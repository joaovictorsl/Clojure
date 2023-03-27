(ns tic-tac-toe.util.getter
  (:require [clojure.string :as str]))

(declare get-row-col-input is-valid? inside-bounds?)

(defn get-valid-position
  [grid]
  (loop
    [input (get-row-col-input)]
    (if (is-valid? input grid)
      (vec (map #(- (Integer/parseInt %) 1) (str/split input #" ")))
      (do
        (println "Invalid input, your input should look like \"row col\" in which row and coll are integers inside the bounds of the grid and not yet marked. Try again.")
        (recur (get-row-col-input))))))

(defn- get-row-col-input
  []
  (println  "Select where you'll mark. Use \"row col\" to select where to mark.")
  (read-line))

(defn- get-keep-game-going?
  []
  (println "Do you wish to continue? (1 means yes, everything else means no)")
  (let [answer (read-line)]
    (= answer "1")))

(defn- is-valid?
  [input grid]
  (and
    (re-matches #"^\d+\s\d+$" input)
    (inside-bounds? input grid)))

(defn- target-is-not-marked
  [grid row col]
  (let [line (grid (- row 1))
        idx (- col 1)]
    (= " " (line idx))))

(defn- inside-bounds?
  [input grid]
  (let [width (count (grid 0))
        height (count grid)
        row (get (str/split input #" ") 0)
        col (get (str/split input #" ") 1)
        row (Integer/parseInt row)
        col (Integer/parseInt col)]
    (and (<= row height)
         (> row 0)
         (<= col width)
         (> col 0)
         (target-is-not-marked grid row col))))
