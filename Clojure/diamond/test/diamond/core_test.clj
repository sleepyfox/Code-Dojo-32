(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]))

(def space (char 32))

(defn n-spaces [n]
  (if (<= n 0) "" 
    (apply str (repeat n space))))

(defn previous-char [c] 
  (if (= c \A) nil
    (char (- (int c) 1))))

(defn char-range [start end]
  (map char (range (int start) (inc (int end)))))

; Calculations for edge and middle spaces
; 0 ..A..  2,-1 (w-1/2)-R, -1+2R
; 1 .B.B.  1,1 (w-1/2)-R, -1+2R
; 2 C...C  0,3 (w-1/2)-R, -1+2R
;
; 0 ...A...  3,-1 (w-1/2)-R, -1+2R
; 1 ..B.B..  2,1  (w-1/2)-R, -1+2R
; 2 .C...C.  1,3  (w-1/2)-R, -1+2R
; 3 D.....D  0,5  (w-1/2)-R, -1+2R
; 
; width = (letter-65)*2+1

(defn make-row [width letter]
  (def row (- (int letter) 65))
  (def edge-spaces (- (/ (- width 1) 2) row))
  (def middle-spaces (+ -1 (* 2 row)))
  (let [s (n-spaces edge-spaces) m (n-spaces middle-spaces)]
    (if (= letter \A)
      (str s letter s)
      (str s letter m letter s))))

(defn make-rows [letter]
  (def width (inc (* (- (int letter) 65) 2)))
  (def new-row (partial make-row width))
  (map new-row (char-range \A letter)))

(defn first-part [c] 
  (def steps (- (int c) 65))
  (if (= steps 0) nil 
    (if (= steps 1) [" A "] ["  A  " " B  B "])))

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