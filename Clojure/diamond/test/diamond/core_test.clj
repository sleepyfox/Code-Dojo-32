(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]))

(def space (char 32))

(defn previous-char [c] 
  (if (= c \A) nil
    (char (- (int c) 1))))

(defn first-part [c] 
  (str " " (str (previous-char c)) " "))

(defn last-part [c] 
  (first-part c))

(defn middle-row [c] 
  (def square-width (+ 1 (* 2 (- (int c) 65))))
  (def middle-row-spaces (apply str (repeat (- square-width 2) space)))
  (str c middle-row-spaces c))

(defn print-diamond [c]
  (if (= c \A) ["A"]
    [(first-part c) (middle-row c) (last-part c)]))

(deftest previous-char-test
  (testing "previous-char 'A' is nil"
    (is (= (previous-char \A) nil)))
  (testing "previous-char 'B' is 'A'"
    (is (= (previous-char \B) \A)))
)

(deftest diamond-test-A
  (testing "print-diamond 'A' returns 'A'"
    (is (= (print-diamond \A) ["A"])))
)

(deftest diamond-test-B
  (testing "print-diamond 'B' returns a list of three strings"
    (is (= (count (print-diamond \B)) 3)))
  (testing "print-diamond 'B' first string is ' A '"
    (is (= (first (print-diamond \B)) " A ")))
  (testing "print-diamond 'B' middle string is 'B B'"
    (is (= (nth (print-diamond \B) 1) "B B")))
  (testing "print-diamond 'B' last string is ' A '"
    (is (= (nth (print-diamond \B) 2) " A ")))
)
