(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]))

(defn previous [c] 
  (if (= c \A) 0 
    (char (- (int c) 1))))

(defn first-row [c] 
  (str " " (previous c) " "))

(defn last-row [c] 
  (first-row c))

(defn middle-row [c] 
  (str c c c))

(defn print-diamond [c]
  (if (= c \A) ["A"]
    [(first-row c) (middle-row c) (last-row c)]))

(deftest previous-test
  (testing "previous 'A' is 0"
    (is (= (previous \A) 0)))
  (testing "previous 'B' is 'A'"
    (is (= (previous \B) \A)))
)

(deftest diamond-test
  (testing "print-diamond 'A' returns 'A'"
    (is (= (print-diamond \A) ["A"])))
  (testing "print-diamond 'B' returns a list of three strings"
    (is (= (count (print-diamond \B)) 3)))
  (testing "print-diamond 'B' first string is ' A '"
    (is (= (first (print-diamond \B)) " A ")))
  (testing "print-diamond 'B' middle string is 'BBB'"
    (is (= (nth (print-diamond \B) 1) "BBB")))
  (testing "print-diamond 'B' last string is ' A '"
    (is (= (nth (print-diamond \B) 2) " A ")))
)