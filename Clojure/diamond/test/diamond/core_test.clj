(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]))

(defn print-diamond [a]
  (if (= a "A") ["A"]
    (if (= a "B") [" A ", "BBB", " A "] 0)))

(deftest a-test
  (testing "print-diamond 'A' returns 'A'"
    (is (= (print-diamond "A") ["A"])))
  (testing "print-diamond 'B' returns a list of three strings"
    (is (= (count (print-diamond "B")) 3)))
  (testing "print-diamond 'B' first string is ' A '"
    (is (= (first (print-diamond "B")) " A ")))
  (testing "print-diamond 'B' middle string is 'BBB'"
    (is (= (nth (print-diamond "B") 1) "BBB")))
  (testing "print-diamond 'B' last string is ' A '"
    (is (= (nth (print-diamond "B") 2) " A ")))
)