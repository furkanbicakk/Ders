	;KODLAMA SABLONU

	list		p=16f877A	; hangi pic
	#include	<p16f877A.inc>	; SFR register 'lar?n tan?mland??? kutuphane
	
	__CONFIG H'3F31'

; WDT, ossilatör gibi register ayarlar?

	
;KULLANILACAK DEGISKENLER

GECIKME1	EQU	0x20    ;GEC?KME 1. DONGU
GECIKME2	EQU	0x21    ;GECIKME 2. DONGU
GECIKME3	EQU	0x22    ;GECIKME 3.DONGU
GECIKME4	EQU	0x23	;TMR GECIKMESI ICIN (BELKI YAZILIM GECIKME KULLANILABILIR DIYE)




;***** Kesme durumunda kaydedilmesi gereken SFR ler icin kullanilacak yardimci degiskenler
w_temp		EQU	0x7D		
status_temp	EQU	0x7E		
pclath_temp	EQU	0x7F					


;********************************************************************************************
	ORG     0x000             	; Baslama vektoru

	nop			  			  	; ICD ozelliginin aktif edilmesi icin gereken bekleme 
  	goto    BASLA              	; baslama etiketine gir

	
;**********************************************************************************************
	ORG     0x004             	; kesme vektoru

	MOVWF   w_temp            	; W n?n yedegini al
	MOVF	STATUS,w          	; Status un yedegini almak icin onu once W ya al
	MOVWF	status_temp       	; Status u yedek register '?na al
	MOVF	PCLATH,w	  		; PCLATH '? yedeklemek icin onu once W 'ya al
	MOVWF	pclath_temp	  		; PCLATH '? yedek register a al
	
	BANKSEL	PIR1			; TMR1 F BIT KONTROLU PIR1 DE
	BCF	PIR1,0			; TMR1 YENI KESMELERI YAKALAYABILSIN DIYE SIFIRLANMALI
	MOVLW	0xDB			; TEKRARDAN 500msn GEC?KME ICIN tmr1 62500(65535-62500=3035)
	MOVWF	TMR1L			; LOW DEGERI YUKLE
	MOVLW	0x0B			; HIGH DEGERI YUKLE
	MOVWF	TMR1H
	
	INCF	GECIKME4,1		; 2 YE ULASMAK ICIN ARTTIR
	
	MOVLW	d'2'			; GECIKME4 2 YE ULASTIMI ()
	SUBWF	GECIKME4,W		
	BTFSS	STATUS,Z
	GOTO	DONUS			 ; EGER ULASMADI ISE CALISMAYA DEVAM
	CLRF	GECIKME4		 ; ULASTI ISE TEKRARDAN GECIKME4 U 2 YE SAYMASI ICIN SIFIRLA
	
	BCF	STATUS,DC		; PORTB NIN ?LK DORT BITI 15 OLDUMU DIYE KONTROL EDILECK DIYE SIFIRLA
	MOVLW	0x01			; STATUS DC KULLANMAK ICIN ADDLW, ADDWF GIBI KOMUTLAR GEREKLI
	ADDWF	PORTB,1			; PORTB ARTISI BINARY GOSTER
	
	BTFSS	STATUS,DC		; EGER PORTB 15 OLDU ISE SIFIRLA
	GOTO	DONUS
	CLRF	PORTB
	
DONUS
	MOVF	pclath_temp,w	  	; Geri donmeden once tum yedekleri geri yukle
	MOVWF	PCLATH		  		
	MOVF    status_temp,w     	
	MOVWF	STATUS            	
	SWAPF   w_temp,f
	SWAPF   w_temp,w 
	RETFIE                    	; Kesme 'den don
;***********************************************************************************************
	
;**************************TMR SIZ 1 SN GECIKME*****************************************************

GECIKME				; (herbir komut 4mhz de 1mikrosn) = 1 sn GEC?KME (yakla??k)
	MOVLW	0x08
	MOVWF	GECIKME1	
	
	MOVLW	0x2F
	MOVWF	GECIKME2
	MOVLW	0x03
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
;****************************************************************************************************************
	
;*****************************************************************************************************

KATOT_LOOKUP
	ADDWF	PCL,1			    ;W DAK? DEGERI PROGRAM COUNTER PCL INE EKLE VE UYGUN DISPLAY CIKTISINI W YA AL
	RETLW	0x3F
	RETLW	0x06
	RETLW	0x5B
	RETLW	0x4F
	RETLW	0x66
	RETLW	0x6D
	RETLW	0x7D
	RETLW	0x07
	RETLW	0x7F
	RETLW	0x6F

;*****************************************************************************************************
;*******************************************************************************************************
	
BASLA

	
	BANKSEL	TRISB
	CLRF	TRISB
	BANKSEL	T1CON
	MOVLW	b'00110001'	    ;PRESCALAR 1:8
	MOVWF	T1CON
	
	BCF	PIR1,TMR1IF	    ;NE OLUR NE OLMAZ. BASTA TMR1 KESME BAYRAGI 0 DAN BASLASIN
	
	BANKSEL	PIE1
	BSF	PIE1,TMR1IE
	
	BANKSEL	PORTB
	CLRF	PORTB
		    
					; => 4MHz icin prescalar ile tmr cycle 8 microsn. 8x62.500=500msn
	MOVLW	0xDB			; bUNUN ICIN tmr1 62500(YANI 65535-62500=3035 (0x0BDB))
	MOVWF	TMR1L			; LOW DEGERI YUKLE
	MOVLW	0x0B			; HIGH DEGERI YUKLE
	MOVWF	TMR1H
	CLRF	GECIKME4	    ; GECIKME4 DEGISKENI 2 KEZ TMR1 I CALISTIRACAK VE 1SN GECIKME OLACAK
	
	MOVLW	b'11000000'	    ; GLOBAL VE CEVRESEL KESMELER AKTIF
	MOVWF	INTCON
	
	
CALIS
	GOTO	CALIS		    ;KESME OLMASINI BEKLE
	
	END                       ; Program sonu




















