; Bu ornekte gecikme, timer ile degil dongu ile yap?lacakt?r
; 4 MHz kristal icin her bir komut 1mikrosn
; ?c ?ceyaklas?k 1sn gecikme olur. 
; NOP bir cycle bekleme yapaca?? unutulmamal?
	
	
; Her bir sn de portB bir bit sola kay?yor
	
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

BASLA

	BANKSEL	TRISB		    ; portb nin hepsini ç?k?? yap
	CLRF	TRISB
	BCF	STATUS,RP0	    ; Portb ye deger yuklemek icin bank1 e gec
	CLRF	PORTB		    ; portb de bir?ey olms?n
YUKLE
	MOVLW	0X01		    ; PORT B 'YI BASTA 00000001 YAP
	MOVWF	PORTB
DD
	BTFSC	PORTB,7		    ; EGER TUM SOLA KAYMALAR BITMIS ISE EN SON BIT 1 OLMALI. O ZAMAN PORTB YE TEKKRAR 01 YUKLE
	GOTO	ULASTI
	CALL	GECIKME		    ; 1sn gecik
	RLF	PORTB		    ; PORTB yi SOLA KAYDIR
	GOTO	DD
ULASTI	
	CALL	GECIKME		    ; 1sn gecik
	GOTO	YUKLE

	END			    ; Program sonu











