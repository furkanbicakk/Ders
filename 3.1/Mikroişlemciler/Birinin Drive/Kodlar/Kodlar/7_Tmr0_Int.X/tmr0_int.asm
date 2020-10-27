	;KODLAMA SABLONU

	list		p=16f877A	; hangi pic
	#include	<p16f877A.inc>	; SFR register 'lar?n tan?mland??? kutuphane
	
	__CONFIG H'3F31'

; WDT, ossilatör gibi register ayarlar?

	
;KULLANILACAK DEGISKENLER

GECIKME1	EQU	0x20    ;GEC?KME 1. DONGU
GECIKME2	EQU	0x21    ;GECIKME 2. DONGU
GECIKME3	EQU	0x22    ;GECIKME 3.DONGU
GECIKME4	EQU	0x23	;TMR0 GECIKMESI ICIN (BELKI YAZILIM GECIKME KULLANILABILIR DIYE)




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
	
	BCF	INTCON,TMR0IF		; TMR0 YENI KESMELERI YAKALAYABILSIN DIYE SIFIRLANMALI
	MOVLW	d'131'			; TEKRARDAN 1msn GEC?KME ICIN
	MOVWF	TMR0			; TMR0 A 131 YUKLE
	
	INCF	GECIKME4,1		; 250 YE ULASMAK ICIN ARTTIR
	
	MOVLW	d'250'			; GECIKME4 250 YE ULASTIMI
	SUBWF	GECIKME4,W		
	BTFSS	STATUS,Z
	GOTO	DONUS			; EGER ULASMADI ISE CALISMAYA DEVAM
	CLRF	GECIKME4		; ULASTI ISE TEKRARDAN GECIKME4 U 250 YE SAYMASI ICIN SIFIRLA
	
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
	MOVLW	b'00000010' ;PRESCALAR 1:8
	MOVWF	OPTION_REG
	
	BCF	STATUS,RP0
	CLRF	PORTB
	MOVLW	d'130'		   ; => 4MHz icin prescalar ile tmr cycle 8 microsn. 8x(256-131)=1msn
	MOVWF	TMR0
	CLRF	GECIKME4	    ; GECIKME4 DEGISKENI 250 KEZ TMR0 I CALISTIRACAK VE 250msn GECIKME OLACAK
	
	BSF	INTCON,T0IE	    ;TMR0 DAN GELECEK KESMELER AKTIF
	BSF	INTCON,GIE	    ;TÜM KESMELER AKTIF
	
CALIS
	GOTO	CALIS		    ;KESME OLMASINI BEKLE
	
	END                       ; Program sonu

















