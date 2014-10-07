;Alejandro Menocal
;CS410115
;ini.scm

;eqv? 
(define (eqv? x y)
  (if (string? x) (if (string? y) (string=? x y) #f)
 (if (number? x) (if (number? y) (= x y) #f))))  
    
;equal?

;=
(define (= . l)
  (if (null? l) 0
      (cond 
        ((zero? (- (car l) (car (cdr l)))))
        (else #f))))
;<
(define (< . l)  
  (if (null? l) 0
      (cond
        ((negative? (- (car l) (car (cdr l)))))
        (else #f))))
;>
(define (> . l)
  (if (null? l) 0
      (cond
        ((positive? (- (car l) (car (cdr l)))))
        (else #f))))
;<=
(define (<= . l)
  (if (null? l) 0
      (cond
        ((zero? (- (car l) (car (cdr l)))))
        ((negative? (- (car l) (car (cdr l)))))
        (else #f))))
;>=
(define (>= . l)
  (if (null? l) 0
      (cond
        ((zero? (- (car l) (car (cdr l)))))
        ((positive? (- (car l) (car (cdr l)))))
        (else #f))))
;zero?
(define (zero? n)
  (cond ((null? n) #f)
        ((= n 0) #t)
        (else #f)))
;positive?
(define (pos? n)
  (cond ((null? n) #f)
        ((> n 0) #t)
        (else #f)))
;negative?
(define (neg? n)
  (cond ((null? n) #f)
        ((< n 0) #t)
        (else #f)))
;odd?
(define (odd? n)
  (if (eqv? n 0) #f
      (even? (- n 1))))
;even?
(define (even? n)
  (if (eqv? n 0) #t
      (odd? (- n 1))))
;max
(define (maxi . l)
  (if (> (car l) (car (cdr l))) (car l)
      (maxi (cdr l))))
;min
(define (min n)
  (cond ((null? (cdr n) (car n))
         ((< (car n) (min (cdr n))) (car n))
         (else (min (cdr n))))))
;+
(define (+ . l)
  (if (null? l) 0
      (b+ (car l) (apply + (cdr l)))))
;-
(define (- . l)
  (if (null? l) 0
      (b- (car l) (apply - (cdr l)))))
;*
(define (* . l)
  (if (null? l) 0
      (b* (car l) (apply * (cdr l)))))
;not
;and
;or
;caar
(define (caar l) (car (car l)))
;cadr
(define (cadr l) (car (cdr l)))
;cdar
(define (cdar l) (cdr (car l)))
;cddr
(define (cddr l) (cdr (cdr l)))
;caaar
(define (caaar l) (car (car (car l))))
;caadr
(define (caadr l) (car (car (cdr l))))
;cadar
(define (cadar l) (car (cdr (car l))))
;cdaar
(define (cdaar l) (cdr (car (car l))))
;caddr
(define (caddr l) (car (cdr (cdr l))))
;cdadr
(define (cdadr l) (cdr (car (cdr l))))
;cddar
(define (cddar l) (cdr (cdr (car l))))
;cdddr
(define (cdddr l) (cdr (cdr (cdr l))))
;caaadr
(define (caaadr l) (car (car (car (cdr l)))))
;caadar
(define (caadar l) (car (car (cdr (car l)))))
;cadaar
(define (cadaar l) (car (cdr (car (car l)))))
;cadadr
(define (cadadr l) (car (cdr (car (cdr l)))))
;cdaadr
(define (cdaadr l) (cdr (car (car (cdr l)))))
;cdaddr
(define (cdaddr l) (cdr (car (cdr (cdr l)))))
;cddadr
(define (cddadr l) (cdr (cdr (car (cdr l)))))
;cadddr
(define (cadddr l) (car (cdr (cdr (cdr l)))))
;caaddr
(define (caaddr l) (car (car (cdr (cdr l)))))
;cdddar
(define (cdddar l) (cdr (cdr (cdr (car l)))))
;cddaar
(define (cddaar l) (cdr (cdr (car (car l)))))
;cdaaar
(define (cdaaar l) (cdr (car (car (car l)))))
;cdadar
(define (cdadar l) (cdr (car (cdr (car l)))))
;caddar
(define (caddar l) (car (cdr (cdr (car l)))))
;cddddr
(define (cddddr l) (cdr (cdr (cdr (cdr l)))))
;caaaar
(define (caaaar l) (car (car (car (car l)))))
;list
;length
(define (length l)
     (cond ((null? l) 0)
          ((+ 1 (length (cdr l))))))
;append
(define (append l1 l2)
     (cond ((null? l1) l2)
         ((cons (car l1)
             (append (cdr l1) l2)))))
;reverse
(define (reverse l)
  (if (null? l) '()
     (append (reverse (cdr l)) (list (car l)))))
;memq
(define (memq n l)
  (if (null? l) #f
      (cond ((or (eq? n (car l)) (member n (cdr l))) l)
            (else #f))))
;memv
(define (memv n l)
  (if (null? l) #f
      (cond ((or (eqv? n (car l)) (member n (cdr l))) l)
            (else #f))))
;member
(define (member n l)
  (if (null? l) #f
      (cond ((or (equal? n (car l)) (member n (cdr l))) l)
            (else #f))))
;assq
(define (assq n l)
  (if (null? l) #f
      (cond ((not (pair? (car l)) ) #f)
            ((and (eq? n (car l)) (assq n (cdr l))) (car l))
            (else #f))))
;assv
(define (assv n l)
  (if (null? l) #f
      (cond ((not (pair? (car l)) ) #f)
            ((and (eqv? n (car l)) (assv n (cdr l))) (car l))
            (else #f))))
;assoc
(define (assoc n l)
  (if (null? l) #f
      (cond ((not (equal? (car l)) ) #f)
            ((and (equal? n (car l)) (assoc n (cdr l))) (car l))
            (else #f))))
;map
;for-each