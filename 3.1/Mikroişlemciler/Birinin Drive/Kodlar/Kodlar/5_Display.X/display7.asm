; Bu ornekte gecikme, timer ile degil dongu ile yap?lacakt?r
;  4 MHz kristal icin her bir komut 1mikrosn
; ic ice yaklas?k 1sn gecikme olur. 
; NOP bir cycle bekleme yapaca?? unutulmamal?
	
; Her bir sn de display 23 gosterip sonuyor
	
	;KODLAMA SABLONU

	list		p=16f877A	; hangi pic
	#include	<p16f877A.inc>	; SFR register 'lar?n tan?mland??? kutuphane
	
	__CONFIG H'3F31'

; WDT, ossilatör gibi register ayarlar?

	
;KULLANILACAK DEGISKENLER

GECIKME1	EQU	0x20    ;GEC?KME 1. DONGU
GECIKME2	EQU	0x21    ;GECIKME 2. DONGU
GECIKME3	EQU	0x22    ;GECIKME 3.DONGU


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

	movwf   w_temp            	; W n?n yedegini al
	movf	STATUS,w          	; Status un yedegini almak icin onu once W ya al
	movwf	status_temp       	; Status u yedek register '?na al
	movf	PCLATH,w	  		; PCLATH '? yedeklemek icin onu once W 'ya al
	movwf	pclath_temp	  		; PCLATH '? yedek register a al

	; gerekli kodlar

	movf	pclath_temp,w	  	; Geri donmeden once tum yedekleri geri yukle
	movwf	PCLATH		  		
	movf    status_temp,w     	
	movwf	STATUS            	
	swapf   w_temp,f
	swapf   w_temp,w          	
	retfie                    	; Kesme 'den don
;***********************************************************************************************

;************************** TMR SIZ 1SN GECIKME ***********************************************

GECIKME				; (herbir komut 4mhz de 1mikrosn) = 1 sn GEC?KME (yakla??k)
	MOVLW	0xE7
	MOVWF	GECIKME1
	MOVLW	0x04
	MOVWF	GECIKME2
DONGU1
	DECFSZ	GECIKME1,1
	GOTO	$+2
	DECFSZ	GECIKME2, 1
	GOTO	DONGU1

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

	CLRF	PORTB		    ; portb de bir?ey olms?n
	
	BANKSEL	TRISB		    ; portb nin hepsini ç?k?? yap
	CLRF	TRISB
	
	MOVLW	0x06		    ;PORTA YI ADC DEGIL I/O YAP
	MOVWF	ADCON1		    
	MOVLW	b'00111100'	    ; PORTA NIN 0 VE 1 I ORTAK KATOTLU DISPLAYLERI KONTROL EDIYOR. DIGERLERI GIRIS OLSUN
	MOVWF	TRISA

	BCF	STATUS,RP0	    ; Portb ye deger yuklemek icin bank1 e gec
	CLRF	PORTB
DD
	MOVLW	0x02		    ; 2. D?SPLAYI AC
	MOVWF	PORTA		    
	MOVLW	d'3'		    ; ONA 3 YAZMAK ICIN LOOKUP A GONDER
	CALL	KATOT_LOOKUP
	MOVWF	PORTB		    ; LOOK-UP DAN GELENI PORTB YE GONDER 
	CALL	GECIKME		    ; SUPURME GECIKMESI
	MOVLW	0x01		    ; 1. DISPLAYI AC
	MOVWF	PORTA		    
	MOVLW	d'2'		    ; 2 YAZMASI ICIN LOOK-UP A GONDER
	CALL	KATOT_LOOKUP	    
	MOVWF	PORTB		    ; LOOK-UP DAN DONEN DEGERI PORTB YE GONDER 
	CALL	GECIKME		    ; SUPURME GECIKMESI
	GOTO	DD

	END                       ; Program sonu











