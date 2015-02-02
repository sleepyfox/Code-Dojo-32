(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]))

(defn print-diamond [a] a)

(deftest a-test
  (testing "print-diamond 'A' returns 'A'"
    (is (= (print-diamond 'A') 'A'))))
