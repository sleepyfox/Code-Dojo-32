(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]))

(def space (char 32))

(defn n-spaces [n]
  (if (= n 0) "" 
    (apply str (repeat n space))))

(defn previous-char [c] 
  (if (= c \A) nil
    (char (- (int c) 1))))

(defn first-part [c] 
  (if (= c \A) nil 
    [(str " " (str (previous-char c)) " ")]))

(defn last-part [c] 
  (first-part c))

(defn middle-row [c] 
  (def square-width (+ 1 (* 2 (- (int c) 65))))
  (def middle-row-spaces (n-spaces (- square-width 2)))
  (str c middle-row-spaces c))

(defn print-diamond [c]
  (if (= c \A) ["A"]
    (flatten [(first-part c) (middle-row c) (last-part c)])))

; Tests here
;
(deftest n-spaces-test
  (testing "n-spaces 0 should return the empty string"
    (is (= (n-spaces 0) "")))
  (testing "n-spaces 1 should return ' '"
    (is (= (n-spaces 1) " ")))
  (testing "n-spaces 5 should return '     '"
    (is (= (n-spaces 5) "     "))))

(deftest previous-char-test
  (testing "previous-char 'A' is nil"
    (is (= (previous-char \A) nil)))
  (testing "previous-char 'B' is 'A'"
    (is (= (previous-char \B) \A))))

(deftest first-part-test 
  (testing "first-part 'A' is nil"
    (is (= (first-part \A) nil)))
  (testing "first-part 'B' is a vector of length 1"
    (is (= (count (first-part \B)) 1)))
  (testing "first-part 'B' is [' A ']"
    (is (= (first (first-part \B)) " A "))))

(deftest diamond-test-A
  (testing "print-diamond 'A' returns 'A'"
    (is (= (print-diamond \A) ["A"]))))

(deftest diamond-test-B
  (testing "print-diamond 'B' returns a list of three strings"
    (is (= (count (print-diamond \B)) 3)))
  (testing "print-diamond 'B' first string is ' A '"
    (is (= (first (print-diamond \B)) " A ")))
  (testing "print-diamond 'B' middle string is 'B B'"
    (is (= (nth (print-diamond \B) 1) "B B")))
  (testing "print-diamond 'B' last string is ' A '"
    (is (= (nth (print-diamond \B) 2) " A "))))
