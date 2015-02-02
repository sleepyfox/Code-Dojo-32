(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]))

(defn previous [c] 
  (if (= c \A) nil
    (char (- (int c) 1))))

(defn first-part [c] 
  (str " " (str (previous c)) " "))

(defn last-part [c] 
  (first-part c))

(defn middle-row [c] 
  (def middle-row-length (+ 1 (* 2 (- (int c) 65))))
  (apply str (repeat middle-row-length c)))

(defn print-diamond [c]
  (if (= c \A) ["A"]
    [(first-part c) (middle-row c) (last-part c)]))

(deftest previous-test
  (testing "previous 'A' is nil"
    (is (= (previous \A) nil)))
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