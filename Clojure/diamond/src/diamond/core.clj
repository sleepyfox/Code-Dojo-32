(ns diamond.core
  (:gen-class))

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

(defn n-spaces [n]
  (if (<= n 0) "" 
    (apply str (repeat n " "))))

(defn char-range [start end]
  (map char (range (int start) (inc (int end)))))

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

(defn print-diamond [c]
  (if (= c \A) 
    ["A"]
    (let [x (make-rows c)]
      (concat x (rest (reverse x))))))
