;******************************************************************************
;*** MIKROISLEMCILER DERSI 2. HAFTA ODEVI 
;*** 170260101 - Mert INCIDELEN
;******************************************************************************

;Gungor Hoca Kodlama Sablonu
	
list		p=16f877A
#include	<p16f877A.inc>
	
__CONFIG H'3F31'
	
;******************************************************************************
;Degiskenler

GECIKME1	EQU	0x20    ;Gecikme 1. Dongu Degiskeni
GECIKME2	EQU	0x21    ;Gecikme 2. Dongu Degiskeni
GECIKME3	EQU	0x22    ;Gecikme 3. Dongu Degiskeni
GECIKME4	EQU	0x23    ;Gecikme 2. Dongu Degiskeni
GECIKME5	EQU	0x24    ;Gecikme 3. Dongu Degiskeni
SECME		EQU	0x25	;Secme Bilgisini Tutma: 
BAS.B		EQU	0x26
BAS.C		EQU	0x27
ORTA.B		EQU	0x28
ORTA.C		EQU	0x29
SON.B		EQU	0x30
SON.C		EQU	0x31

;******************************************************************************
		
ORG     0x000             	; Baslama Vektoru
nop
goto    BASLA              	; BASLA Etiketine Git

;******************************************************************************
;Gecikme Alt Programi		

GECIKME				
	MOVLW	0x08
	MOVWF	GECIKME1	
	
	MOVLW	0x15
	MOVWF	GECIKME2
	MOVLW	0x02
	MOVWF	GECIKME3

    DONGU1
	DECFSZ	GECIKME1,1
	GOTO	$+2
	DECFSZ	GECIKME2,1
	GOTO	$+2
	DECFSZ	GECIKME3,1
	GOTO	DONGU1
	NOP
	NOP
	NOP	
	RETURN
	
GGECIKME				; (herbir komut 4mhz de 1mikrosn) = 1 sn GEC?KME (yakla??k)
	MOVLW	0xE7
	MOVWF	GECIKME3
	MOVLW	0x04
	MOVWF	GECIKME4
DONGU2
	DECFSZ	GECIKME3,1
	GOTO	$+2
	DECFSZ	GECIKME4, 1
	GOTO	DONGU2

	NOP
	NOP	
	RETURN

;******************************************************************************
;Dondurme Alt Programlari

B.YAK
	;SECME 000 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BSF PORTB,0
	
	;SECME 001 DURUMU
	BTFSS SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BSF PORTB,1
	
	;SECME 010 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSS SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BSF PORTB,2
	
	;SECME 011 DURUMU
	BTFSS SECME,0
	GOTO $+2
	BTFSS SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BSF PORTB,3
	
	;SECME 100 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSS SECME,2
	GOTO $+2
	BSF PORTB,4
	
	;SECME 101 DURUMU
	BTFSS SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSS SECME,2
	GOTO $+2
	BSF PORTB,5
	
	;SECME 110 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSS SECME,1
	GOTO $+2
	BTFSS SECME,2
	GOTO $+2
	BSF PORTB,6
	
	RETURN

C.YAK
	;SECME 000 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BSF PORTC,0
	
	;SECME 001 DURUMU
	BTFSS SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BSF PORTC,1
	
	;SECME 010 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSS SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BSF PORTC,2
	
	;SECME 011 DURUMU
	BTFSS SECME,0
	GOTO $+2
	BTFSS SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BSF PORTC,3
	
	;SECME 100 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSS SECME,2
	GOTO $+2
	BSF PORTC,4
	
	RETURN	

B.SONDUR
	;SECME 000 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BCF PORTB,0
	
	;SECME 001 DURUMU
	BTFSS SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BCF PORTB,1
	
	;SECME 010 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSS SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BCF PORTB,2
	
	;SECME 011 DURUMU
	BTFSS SECME,0
	GOTO $+2
	BTFSS SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BCF PORTB,3
	
	;SECME 100 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSS SECME,2
	GOTO $+2
	BCF PORTB,4
	
	;SECME 101 DURUMU
	BTFSS SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSS SECME,2
	GOTO $+2
	BCF PORTB,5
	
	;SECME 110 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSS SECME,1
	GOTO $+2
	BTFSS SECME,2
	GOTO $+2
	BCF PORTB,6
	
	RETURN
	
C.SONDUR
	;SECME 000 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BCF PORTC,0
	
	;SECME 001 DURUMU
	BTFSS SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BCF PORTC,1
	
	;SECME 010 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSS SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BCF PORTC,2
	
	;SECME 011 DURUMU
	BTFSS SECME,0
	GOTO $+2
	BTFSS SECME,1
	GOTO $+2
	BTFSC SECME,2
	GOTO $+2
	BCF PORTC,3
	
	;SECME 100 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSS SECME,2
	GOTO $+2
	BCF PORTC,4
	
	;SECME 101 DURUMU
	BTFSS SECME,0
	GOTO $+2
	BTFSC SECME,1
	GOTO $+2
	BTFSS SECME,2
	GOTO $+2
	BCF PORTC,5
	
	;SECME 110 DURUMU
	BTFSC SECME,0
	GOTO $+2
	BTFSS SECME,1
	GOTO $+2
	BTFSS SECME,2
	GOTO $+2
	BCF PORTC,6
	
	RETURN
	
GOSTER
	MOVF BAS.B,0 ;bas.B degerini getir
	MOVWF	SECME
	CALL B.YAK
	MOVF BAS.C,0 ;bas.C degerini getir
	MOVWF	SECME
	CALL C.YAK
	CALL GGECIKME
	
	MOVF BAS.B,0 ;bas.B degerini getir
	MOVWF	SECME
	CALL B.SONDUR
	MOVF BAS.C,0 ;bas.C degerini getir
	MOVWF	SECME
	CALL C.SONDUR
	
	MOVF ORTA.B,0 ;ORTA.B degerini getir
	MOVWF	SECME
	CALL B.YAK
	MOVF ORTA.C,0 ;bas.C degerini getir
	MOVWF	SECME
	CALL C.YAK
	CALL GGECIKME
	
	MOVF ORTA.B,0 ;ORTA.B degerini getir
	MOVWF	SECME
	CALL B.SONDUR
	MOVF ORTA.C,0 ;bas.C degerini getir
	MOVWF	SECME
	CALL C.SONDUR
	MOVF SON.B,0 ;SON.B degerini getir
	MOVWF	SECME
	CALL B.YAK
	MOVF SON.C,0 ;SON.C degerini getir
	MOVWF	SECME
	CALL C.YAK
	CALL GGECIKME
	MOVF SON.B,0 ;SON.B degerini getir
	MOVWF	SECME
	CALL B.SONDUR
	MOVF SON.C,0 ;SON.C degerini getir
	MOVWF	SECME
	CALL C.SONDUR
	RETURN
	
	
;**********************************************************
	


BAS.B.ARTTIR
	
	INCF BAS.B,1
	
	BTFSS BAS.B,0
	GOTO $+2
	BTFSS BAS.B,1
	GOTO $+2
	BTFSS BAS.B,2
	GOTO $+2
	CLRF BAS.B
	
	RETURN
	
BAS.B.AZALT
	

	MOVLW	b'00000111'
	BTFSC BAS.B,0
	GOTO $+2
	BTFSC BAS.B,1
	GOTO $+2
	BTFSC BAS.B,2
	GOTO $+2
	MOVWF	BAS.B
	
	DECF BAS.B,1
	
	RETURN
	
BAS.C.ARTTIR
	
	INCF BAS.C,1
	
	BTFSS BAS.C,0
	GOTO $+2
	BTFSC BAS.C,1
	GOTO $+2
	BTFSS BAS.C,2
	GOTO $+2
	CLRF BAS.C
	
	RETURN
	
BAS.C.AZALT
	

	MOVLW	b'00000101'
	BTFSC BAS.C,0
	GOTO $+2
	BTFSC BAS.C,1
	GOTO $+2
	BTFSC BAS.C,2
	GOTO $+2
	MOVWF	BAS.C
	
	DECF BAS.C,1
	
	RETURN
		
	
;******************************************************************************
;Baslangic
	
BASLA
	
	;en agirliksiz set edildi 01.banka gecildi
	BSF	STATUS,RP0
	
	;varsayilan adc pasifleme
	MOVLW	0X06	
	MOVWF	ADCON1
	
	; port a'nin giris cikislarini ayarlama
	MOVLW	b'00001111'
	MOVWF	TRISA		
	
	; port b'nin giris cikislarini ayarlama
	MOVLW	0X00	
	MOVWF	TRISB		    
	
	; port c'nin giris cikislarini ayarlama
	MOVLW	0X00
	MOVWF	TRISC	
	
	;en agirliksiz clear edildi portlar icin 00.banka gecildi
	BCF	STATUS,RP0	
	
	;port a, b ve c icini temizleme
	CLRF	PORTA
	CLRF	PORTB
	CLRF	PORTC
	
	;pull-up oldugundan girislere 1 yukle
	MOVLW	b'00001111'	    ; PORT A 'YI BASTA 00000000 YAP
	MOVWF	PORTA


	    
BASLANGIC
	
	;konum ilk durum dikey (0) olarak ayarlama
	
	MOVLW	b'00000010'
	MOVWF	BAS.B
	MOVLW	b'00000011'
	MOVWF	ORTA.B
	MOVLW	b'00000100'
	MOVWF	SON.B
	
	MOVLW	b'00000010'
	MOVWF	BAS.C
	MOVLW	b'00000010'
	MOVWF	ORTA.C
	MOVLW	b'00000010'
	MOVWF	SON.C
	
	MOVLW	0x00
	MOVWF	SECME
	
	;baslangicta butonlari dinleme
	DON
	CALL GOSTER ;supurme ile gosterme
	BTFSS	PORTA,0		
	GOTO	SAGA
	BTFSS	PORTA,1		
	GOTO	ASAGI
	BTFSS	PORTA,2	
	GOTO	YUKARI
	BTFSS	PORTA,3	
	GOTO	SOLA
	GOTO DON
	
;******************************************************************************
;Butona basilma durumlari

SAGA	
	CALL GGECIKME
	
	MOVF ORTA.B,0 ;ORTA.B degerini getir
	MOVWF	SON.B ;SON.B degeri olarak ayarla
	
	MOVF ORTA.C,0 ;ORTA.C degerini getir
	MOVWF	SON.C ;SON.C degeri olarak ayarla
	
	MOVF BAS.B,0 ;BAS.B degerini getir
	MOVWF	ORTA.B ;ORTA.B degeri olarak ayarla
	
	MOVF BAS.C,0 ;BAS.C degerini getir
	MOVWF	ORTA.C ;ORTA.C degeri olarak ayarla
	 
	CALL BAS.C.ARTTIR ;C degerini arttiriyoruz(saga)
	
	DONME1
	    CALL GOSTER ;supurme ile gosterme
	    CALL GOSTER ;supurme ile gosterme
	    CALL GOSTER ;supurme ile gosterme
	    ;portlari dinle
	    BTFSS	PORTA,0		
	    GOTO	SAGA
	    BTFSS	PORTA,1		
	    GOTO	ASAGI
	    BTFSS	PORTA,2	
	    GOTO	YUKARI
	    BTFSS	PORTA,3	
	    GOTO	SOLA
	    ;;;;;;;;;;;;;;;
	GOTO DONME1
	
    GOTO SAGA
	
	

ASAGI
    CALL GGECIKME
    
	MOVF ORTA.B,0 ;ORTA.B degerini getir
	MOVWF	SON.B ;SON.B degeri olarak ayarla
	
	MOVF ORTA.C,0 ;ORTA.C degerini getir
	MOVWF	SON.C ;SON.C degeri olarak ayarla
	
	MOVF BAS.B,0 ;BAS.B degerini getir
	MOVWF	ORTA.B ;ORTA.B degeri olarak ayarla
	
	MOVF BAS.C,0 ;BAS.C degerini getir
	MOVWF	ORTA.C ;ORTA.C degeri olarak ayarla
	
	CALL BAS.B.ARTTIR ; B degerini artt?r?yoruz(asagi)
	
	    DONME2
	    CALL GOSTER ;supurme ile gosterme
	    CALL GOSTER ;supurme ile gosterme
	    CALL GOSTER ;supurme ile gosterme
	    ;portlari dinle
	    BTFSS	PORTA,0		
	    GOTO	SAGA
	    BTFSS	PORTA,1		
	    GOTO	ASAGI
	    BTFSS	PORTA,2	
	    GOTO	YUKARI
	    BTFSS	PORTA,3	
	    GOTO	SOLA
	    ;;;;;;;;;;;;;;;
	    GOTO DONME2
	
GOTO ASAGI
	
	    
YUKARI
	    
	CALL GGECIKME
	
	MOVF ORTA.B,0 ;ORTA.B degerini getir
	MOVWF	SON.B ;SON.B degeri olarak ayarla
	
	MOVF ORTA.C,0 ;ORTA.C degerini getir
	MOVWF	SON.C ;SON.C degeri olarak ayarla
	
	MOVF BAS.B,0 ;BAS.B degerini getir
	MOVWF	ORTA.B ;ORTA.B degeri olarak ayarla
	
	MOVF BAS.C,0 ;BAS.C degerini getir
	MOVWF	ORTA.C ;ORTA.C degeri olarak ayarla
	
	CALL BAS.B.AZALT ; B degerini azaltiyoruz(yukari)
	
	    DONME3
	    CALL GOSTER ;supurme ile gosterme
	    CALL GOSTER ;supurme ile gosterme
	    CALL GOSTER ;supurme ile gosterme
	    ;portlari dinle
	    BTFSS	PORTA,0		
	    GOTO	SAGA
	    BTFSS	PORTA,1		
	    GOTO	ASAGI
	    BTFSS	PORTA,2	
	    GOTO	YUKARI
	    BTFSS	PORTA,3	
	    GOTO	SOLA
	    ;;;;;;;;;;;;;;;
	    
	    GOTO DONME3
	
GOTO YUKARI
	    
SOLA ;SOLA OTELEME ISLEMI
		
	MOVF ORTA.B,0 ;ORTA.B degerini getir
	MOVWF	SON.B ;SON.B degeri olarak ayarla
	
	MOVF ORTA.C,0 ;ORTA.C degerini getir
	MOVWF	SON.C ;SON.C degeri olarak ayarla
	
	MOVF BAS.B,0 ;BAS.B degerini getir
	MOVWF	ORTA.B ;ORTA.B degeri olarak ayarla
	
	MOVF BAS.C,0 ;BAS.C degerini getir
	MOVWF	ORTA.C ;ORTA.C degeri olarak ayarla
	
	CALL BAS.C.AZALT ; C degerini azaltiyoruz(sola)
	
	    DONME4
	    CALL GOSTER ;supurme ile gosterme
	    CALL GOSTER ;supurme ile gosterme
	    CALL GOSTER ;supurme ile gosterme
	    ;portlari dinle
	    BTFSS	PORTA,0		
	    GOTO	SAGA
	    BTFSS	PORTA,1		
	    GOTO	ASAGI
	    BTFSS	PORTA,2	
	    GOTO	YUKARI
	    BTFSS	PORTA,3	
	    GOTO	SOLA
	    ;;;;;;;;;;;;;;;
	    
	    GOTO DONME4
	
GOTO SOLA

END                       ; Program sonu
