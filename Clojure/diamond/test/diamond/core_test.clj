(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]))


; Tests here
;
(deftest n-spaces-test
  (testing "n-spaces -1 should return the empty string"
    (is (= (n-spaces -1) "")))
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

(deftest char-range-test
  (testing "char-range A should be (A)"
    (is (= (char-range \A \A) (list \A))))
  (testing "char-range A E should be (A B C D E)"
    (is (= (char-range \A \E) (list \A \B \C \D \E)))))

(deftest first-part-test 
  (testing "first-part 'A' is nil"
    (is (= (first-part \A) nil)))
  (testing "first-part 'B' is a vector of length 1"
    (is (= (count (first-part \B)) 1)))
  (testing "first-part 'B' is [' A ']"
    (is (= (first (first-part \B)) " A ")))
  (testing "first-part 'C' is a vector of length 2"
    (is (= (count (first-part \C)) 2))))

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

(deftest make-row-test
  (testing "make-row 1 A should return 'A'"
    (is (= (make-row 1 \A) "A")))
  (testing "make-row 3 A should return ' A '"
    (is (= (make-row 3 \A) " A ")))
  (testing "make-row 5 A should return '  A  '"
    (is (= (make-row 5 \A) "  A  ")))
  (testing "make-row 3 B should return 'B B'"
    (is (= (make-row 3 \B) "B B")))
  (testing "make-row 5 B should return ' B B '"
    (is (= (make-row 5 \B) " B B ")))
  (testing "make-row 5 C should return 'C   C'"
    (is (= (make-row 5 \C) "C   C"))))

(deftest make-rows-test
  (testing "make-rows A should return a list of one item"
    (is (= (count (make-rows \A)) 1)))
  (testing "make-rows B should return a list with 2 items"
    (is (= (count (make-rows \B)) 2))))