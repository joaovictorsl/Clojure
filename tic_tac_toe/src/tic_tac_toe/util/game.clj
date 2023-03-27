(ns tic-tac-toe.util.game
  (:require [tic-tac-toe.util.getter :as getter]))

(defn change-player
  [player]
  (if (= (clojure.string/upper-case player) "X")
    "O"
    "X"))

(defn mark-line
  [line col marker]
  (assoc line col marker))

(defn mark-grid
  [grid row col marker]
  (assoc grid row
    (mark-line (grid row) col marker)))

(defn make-move
  [player grid]
  (let [row-col (getter/get-valid-position grid)]
    (mark-grid
      grid
      (row-col 0)                                           ;Row
      (row-col 1)                                           ;Col
      player)))
