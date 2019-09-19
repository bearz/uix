(ns uix.hooks-test
  (:require [clojure.test :refer [deftest is testing run-tests async]]
            [uix.hooks.alpha :as hooks :refer [maybe-js-deps maybe-ret-fn]]
            [uix.dom.alpha :as dom]))

(defn render [el]
  (dom/render el js/root))

(deftest test-maybe-js-deps
  (is (.isArray js/Array (maybe-js-deps [])))
  (is (= (maybe-js-deps nil) js/undefined)))

(deftest test-maybe-ret-fn
  (is (== 1 ((maybe-ret-fn (constantly 1)))))
  (is (== js/undefined ((maybe-ret-fn (constantly nil))))))

(deftest test-state-hook
  (let [f-state (fn [done]
                  (let [state (hooks/state 1)]
                    (is (instance? hooks/StateHook state))
                    (is (or (== @state 1) (== @state 2)))
                    (if (== @state 2)
                      (done)
                      (swap! state inc))))]
    (async done
      (render [f-state done]))))

(deftest test-ref-hook
  (let [f-ref (fn [done]
                (let [ref (hooks/ref 1)]
                  (is (instance? hooks/RefHook ref))
                  (is (== @ref 1))
                  (swap! ref inc)
                  (is (== @ref 2))
                  (done)))]
    (async done
      (render [f-ref done]))))


(defn -main []
  (run-tests))
