(ns tic-tac-toe.core
  (:require [tic-tac-toe.util.formatter :as formatter]
            [tic-tac-toe.util.game :as game]
            [tic-tac-toe.util.getter :as getter]
            [tic-tac-toe.util.judge :as judge]))

(declare start-game resume-game current-points inc-if-player-won)

(def player-1 "X")
(def player-2 "O")

(defn -main
  []
  (loop [first-player player-1
         result (start-game first-player)
         points-1 0
         points-2 0]
    (println result)
    (println current-points (inc-if-player-won result player-1 points-1) (inc-if-player-won result player-2 points-2))
    (when (getter/get-keep-game-going?)
      (recur
        game/change-player first-player
        start-game (game/change-player first-player)
        (inc-if-player-won result player-1 points-1)
        (inc-if-player-won result player-2 points-2)))))

(defn current-points
  [p1 p2]
  (println (str "Player " player-1 " has " p1 " points and Player " player-2 " has " p2 " points.")))

(defn inc-if-player-won
  [result player points]
  (if (= result (str player " won!"))
    (+ points 1)
    points))

(defn start-game
  [first-player]
  (loop [grid [[" " " " " "] [" " " " " "] [" " " " " "]]
         player first-player]
    case
    (if (judge/game-end? grid)
      (judge/get-result grid player)
      (resume-game grid player))))

(defn resume-game
  [grid player]
  (println (formatter/format-grid grid))
  (println (str "It's your turn, player " player "."))
  (recur
    (game/make-move player grid)
    (game/change-player player)))