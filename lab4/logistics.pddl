;; This is a plain STRIPS formulation of the standard Logistics domain.

;; In this domain, there are six different types of objects: "object"
;; (the packages to be transported), "truck", "airplane" and their
;; common supertype "vehicle", "location" and the subtype "airport",
;; and finally "city". Types are defined by static (in the sense that
;; there are no operators that change their truth value) unary predicates.
;; The types of objects in a problem instance must be defined by including
;; the appropriate typing predicates in the initial state.

;; A binary static predicate called "loc" describes the topology of the
;; problem instance: "(loc ?l ?c)" is true iff the location ?l is in city
;; ?c.

(define (domain logistics)
  (:requirements :strips)
  (:predicates

   ;; Static predicates:
   (small_object ?so) (medium_object ?mo) (large_object ?lo)
   (truck ?t) (airplane ?p) (vehicle ?v)
   (location ?l) (airport ?a) (city ?c) (loc ?l ?c)
   (train ?tr) (trainstation ?trs)

   ;; Non-static predicates:
   (at ?x ?l) ;; ?x (package or vehicle) is at location ?l
   (in ?p ?v) ;; package ?p is in vehicle ?v
   )

  ;; Actions for loading and unloading packages.
  ;; By declaring all trucks and airplanes to be also "vehicle", we
  ;; can use the same load/unload operator for both (otherwise we
  ;; would need one for each subtype of vehicle).
  (:action load_small
    :parameters (?so ?v ?l)
    :precondition (and (small_object ?so) (vehicle ?v) (location ?l)
		       (at ?v ?l) (at ?so ?l))
    :effect (and (in ?so ?v) (not (at ?so ?l))))

   (:action load_medium
    :parameters (?mo ?v ?l)
    :precondition (and (medium_object ?mo) (vehicle ?v) (location ?l)
		       (at ?v ?l) (at ?mo ?l))
    :effect (and (in ?mo ?v) (not (at ?mo ?l))))


    (:action load_large_train
    :parameters (?lo ?tr ?l)
    :precondition (and (large_object ?lo) (train ?tr) (location ?l)
		  (at ?tr ?l) (at ?lo ?l))
    :effect (and (in ?lo ?tr) (not (at ?lo ?l))))

    (:action load_large_truck
    :parameters (?lo ?t ?l)
    :precondition (and (large_object ?lo) (truck ?t) (location ?l)
		       (at ?t ?l) (at ?lo ?l))
    :effect (and (in ?lo ?t) (not (at ?lo ?l))))



  (:action unload_small
    :parameters (?so ?v ?l)
    :precondition (and (small_object ?so) (vehicle ?v) (location ?l)
		       (at ?v ?l) (in ?so ?v))
    :effect (and (at ?so ?l) (not (in ?so ?v))))

   (:action unload_medium
    :parameters (?mo ?v ?l)
    :precondition (and (medium_object ?mo) (vehicle ?v) (location ?l)
		       (at ?v ?l) (in ?mo ?v))
    :effect (and (at ?mo ?l) (not (in ?mo ?v))))


   (:action unload_large_truck
    :parameters (?lo ?t ?l)
    :precondition (and (large_object ?lo) (truck ?t) (location ?l)
		       (at ?t ?l) (in ?lo ?t))
    :effect (and (at ?lo ?l) (not (in ?lo ?t))))

   (:action unload_large_train
    :parameters (?lo ?tr ?l)
    :precondition (and (large_object ?lo) (train ?tr) (location ?l)
		       (at ?tr ?l) (in ?lo ?tr))
    :effect (and (at ?lo ?l) (not (in ?lo ?tr))))


  ;; Drive a truck between two locations in the same city.
  ;; By declaring all locations, including airports, to be of type
  ;; "location", we can use only one driving operator (otherwise,
  ;; we would again need one for each case, i.e. one for from-location-
  ;; to-airport, one for from-location-to-location, etc. Very
  ;; unnecessay).
  (:action drive
    :parameters (?t ?l1 ?l2 ?c)
    :precondition (and (truck ?t) (location ?l1) (location ?l2) (city ?c)
		       (at ?t ?l1) (loc ?l1 ?c) (loc ?l2 ?c))
    :effect (and (at ?t ?l2) (not (at ?t ?l1))))

  ;; Fly an airplane between two airports.
  (:action fly
    :parameters (?p ?a1 ?a2)
    :precondition (and (airplane ?p) (airport ?a1) (airport ?a2)
		       (at ?p ?a1))
    :effect (and (at ?p ?a2) (not (at ?p ?a1))))
  (:action drivetrain
    :parameters (?tr ?trs1 ?trs2)
    :precondition (and (train ?tr) (trainstation ?trs1) (trainstation ?trs2) 
    (at ?tr ?trs1))
    :effect (and (at ?tr ?trs2) (not (at ?tr ?trs1))))
    )
