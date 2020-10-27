  ; KODLAMA SABLONU

	list		p=16f877A	; hangi pic
	#include	<p16f877A.inc>	; SFR register 'lar?n tan?mland??? kutuphane
	
	__CONFIG _CP_OFF & _WDT_OFF & _BODEN_OFF & _PWRTE_ON & _XT_OSC & _WRT_OFF & _LVP_ON & _CPD_OFF

; WDT, ossilatör gibi register ayarlar?

	
;KULLANILACAK DEGISKENLER

bizimDegisken	EQU 0x20   ;Buradaki ismi degistirebilir ve yenileri eklenebilir


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

	BCF	INTCON,INTF		; SONRAK? KESMELER? YAKALAMAK ?Ç?N TEKRAR INTF=0 
	MOVLW	b'01100000'		; ÖN SAYI YÜKLE
	BTFSS	PORTB,7			; EGER SON DORT YANIYORSA DESEN? DE???T?R (SON BITE BAK VE ANLA)
	MOVLW	b'11110000'		; EGER SON DORT YANMIYORSA (K? BUNDA 7.B?T DE YANMIYOR DEMEK)
	MOVWF	PORTB

	movf	pclath_temp,w	  	; Geri donmeden once tum yedekleri geri yukle
	movwf	PCLATH		  		
	movf    status_temp,w     	
	movwf	STATUS            	
	swapf   w_temp,f
	swapf   w_temp,w          	
	retfie                    	; Kesme 'den don
;***********************************************************************************************
ON_AYARLAR
	CLRF	PORTB
	MOVLW	b'10010000'	    ;INTE 'Y? KULLAN. GIE AKT?F
	MOVWF	INTCON
	
	BANKSEL	OPTION_REG
	BCF	OPTION_REG,INTEDG   ;DÜSEN KENAR TET?KLEMES? 2.ADIM
	
	MOVLW	b'00000001'	    ;HAZIR BANK 1 'DE ?KEN PORTB AYARINI DA YAP
	MOVWF	TRISB		    
		
	BCF	STATUS,RP0	    ;BANK 0 'A GEÇ
	MOVLW	0xF0
	MOVWF	PORTB

	RETURN

BASLA
	CALL ON_AYARLAR

DONGU
	GOTO DONGU

	END                       ; Program sonu                


