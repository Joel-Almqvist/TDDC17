;; This is a small problem instance for the standard Logistics domain,
;; as defined in "logistic.pddl".

(define (problem C3_2)
  (:domain logistics)
  (:objects
   city1 city2 city3 city4 city5  
   truck1 truck2 truck3 
   truck4 truck5
   airplane2 airplane4
   office1 office2 office3
   office4 office5       
   airport2 airport3 airport4 
   packet1 packet2 packet5       
   train1 train5	
   trainstation1
   trainstation2
   trainstation4	    
   trainstation5
   )
  (:init
   ;; Type declarations:
   (small_object packet1) (medium_object packet5) (large_object packet2)

   ;; all vehicles must be declared as both "vehicle" and their
   ;; appropriate subtype,
   (vehicle truck1) (vehicle truck2) (vehicle truck3)
   (vehicle truck4) (vehicle truck5)
   (truck truck1) (truck truck2) (truck truck3) (truck truck4) (truck truck5)

   ;; Create airplanes
   (vehicle airplane2) (airplane airplane2) (vehicle airplane4) (airplane airplane4)
   (vehicle train1) (vehicle train5)
   (train train1) (train train5)

   ;; likewise, airports must be declared both as "location" and as
   ;; the subtype "airport",
   (location office1) (location office2) (location office3)
   (location office4) (location office5)

   (location airport2) (location airport3) (location airport4)
   (airport airport2) (airport airport3) (airport airport4)

   (city city1) (city city2) (city city3) (city city4) (city city5)

   (location trainstation1) (location trainstation5)
   (location trainstation2) (location trainstation4)
   (trainstation trainstation1) (trainstation trainstation5)
   (trainstation trainstation2) (trainstation trainstation4)

   ;; "loc" defines the topology of the problem,
   (loc office1 city1) (loc office2 city2) (loc office3 city3)
   (loc office4 city4) (loc office5 city5)

   (loc airport2 city2) (loc airport3 city3) (loc airport4 city4)

   (loc trainstation1 city1) (loc trainstation5 city5)
   (loc trainstation2 city2) (loc trainstation4 city4)

   ;; The actual initial state of the problem, which specifies the
   ;; initial locations of all packages and all vehicles:
   (at packet1 office1)
   (at packet2 office2)
   (at packet5 office5)
   (at truck1 trainstation1)
   (at truck2 airport2)
   (at truck3 office3)
   (at truck4 trainstation4)
   (at truck5 trainstation5)
   (at airplane2 airport2)
   (at airplane4 airport4)
   (at train1 trainstation1)
   (at train5 trainstation5)
   )

  ;; The goal is to have both packages delivered to their destinations:
  (:goal (and (at packet1 office3) (at packet5 office3) (at packet2 office5)))
  )
