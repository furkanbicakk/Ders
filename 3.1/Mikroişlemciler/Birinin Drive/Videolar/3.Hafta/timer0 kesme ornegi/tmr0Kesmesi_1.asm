; KODLAMA SABLONU

	list		p=16f877A	; hangi pic
	#include	<p16f877A.inc>	; SFR register 'lar?n tan?mland??? kutuphane
	
	__CONFIG _CP_OFF & _WDT_OFF & _BODEN_OFF & _PWRTE_ON & _RC_OSC & _WRT_OFF & _LVP_ON & _CPD_OFF

; WDT, ossilatör gibi register ayarlar?

	
;KULLANILACAK DEGISKENLER

TUR_DEGISKENI1	EQU 0x20   ;TMR0 ÖLÇÜMÜ ?Ç?N TUR SAYACAK B?R?NC? DE???KEN -1.ADIM-
TUR_DEGISKENI2	EQU 0x21   ;TMR0 ÖLÇÜMÜ ?Ç?N TUR SAYACAK 2.DE???KEN -1.ADIM-


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

	;**** TMR0 DA 1msn DOLUNCA BU KESME KODLARI ÇALISACAKTIR
	BCF	INTCON,T0IF		; ÖNCE TMR0 'IN BAYRA?I SIFIRLANIR KI B?R SONRAK? TMR0 KESMESI YAKALANSIN
	
	MOVLW	d'250'			; TMR0 250 DEFA TUR ATTI MI? (YANI 250msn GEÇTI MI?)
	SUBWF	TUR_DEGISKENI1		
	
	BTFSS	STATUS,C		;EGER SONUC SIFIR ISE CEVAP EVET. O ZAMAN SN KONTROLA ATLA
	GOTO	DEVAM
	
	DECFSZ	TUR_DEGISKENI2		; O ZAMAN 4 DEFA 250 TUR ATTI MI? (YANI 1sn OLDU MU?)
	GOTO	DEVAM
	
	CALL	BIR_SN_OLDU		;1sn OLUNCA CALISACAK PROGRAMI CALISTIRABILIRSIN
	
	MOVLW	d'4'			; TEKRARDAN 1SN SAYAB?LMEK ?Ç?N SIFRLANAN DEG?SKENI GUNCELLE
	MOVWF	TUR_DEGISKENI2
DEVAM
	movf	pclath_temp,w	  	; Geri donmeden once tum yedekleri geri yukle
	movwf	PCLATH		  		
	movf    status_temp,w     	
	movwf	STATUS            	
	swapf   w_temp,f
	swapf   w_temp,w          	
	retfie                    	; Kesme 'den don
;***********************************************************************************************


BASLA
	CALL ON_AYARLAR		;BASLAMADAN ÖNCE GEREKLI TMR0 AYARLARINI VE KESMELERI AKTIF ET

DONGU
	GOTO DONGU		;KILITLEN VE KESME OLURSA KESME VEKTÖRÜNE GIT
	
	
	
;**************************** TMR0 KESME AYAR ALT PROGRAMI*****************************
	;Bu alt program TMR0 'a her 1msn 'de bir kesme ürettirir (Tabi 4MHz kristal ve 1:8 prescalar icin)
	;Bu süre istenildigi kadar tur att?irilarak 
ON_AYARLAR			    
	BANKSEL	OPTION_REG	    ;OPTION_REG 'IN BULUNDUGU BANK 'A GEC
	
	MOVLW	b'00000010'	    ;OPTION REG DE PSA TMR0 ICIN AYARLANDI ve PRESCALAR 1:8
	MOVWF	OPTION_REG	    ;TMR0 KESMESI IÇIN 2.ADIM
	
	CLRF	TRISB		    ;HAZIR BANK 1 'DEYKEN PORTB NIN HEPSINI ÇIKIS YAP 3.ADIM
	
	BANKSEL	PORTB		    ;BANK1 E GEÇ VE PORTB YE BASTA 0 YÜKLE
	CLRF	PORTB
	
	MOVLW	0x82		    ;TMR0 'IN 4MHZ 'DE 1msn LIK KESME ÜRETEBILMESI IÇIN 
	MOVWF	TMR0		    ;125 TANE SAYMALI BU NEDENLE 130 YUKLE 4.ADIM
  
	MOVLW	d'250'		    ;1sn SAYMAK ICIN 1000 SEFER TMR0 ÇALISMALI. 
	MOVWF	TUR_DEGISKENI1	    ;BUNUN ICIN IKI DEGISKEN KULLANILIR. BIRINCISINDE 250 ?K?NC?S?NE 4 ATAYABILIRIZ
	MOVLW	d'4'		    ;4x250 =1000msn (1sn)
	MOVWF	TUR_DEGISKENI2
	
	BSF	INTCON,T0IE	    ;TMR0 DAN GELECEK KESMELER AKTIF
	BSF	INTCON,GIE	    ;TÜM KESMELER AKTIF
	RETURN
;**************************** TMR0 KESME AYAR ALT PROGRAMI SONU ************************************	

	
	
;**************************** TOPLAMDA 1 SN OLUNCA GEREKLI ISLEMLERI YAPMA ALT FONKSIYONU************
	
;**************************** TOPLAMDA 1 SN OLUNCA GEREKLI ISLEMLERI YAPMA ALT FONKSIYONU************
BIR_SN_OLDU
	BANKSEL	PORTB
	
	MOVLW	0x01	    ;HAZIRDA BIR ATANMIS DEGERI BEKLET
	BTFSC	PORTB,0	    ;EGER PORTB 'NIN ?LK BITI 0 ?SE W=1 'I PORTB 'YE YUKLE
	CLRW		    ;YOK E?ER PORTB 'NIN ?LK BITI 1 ISE W=0 YAP VE YUKLE
	MOVWF	PORTB
	
	RETURN
	

;***************************** BIR_SN_OLDU ALT PROGRAMI SONU*****************************************		
	
	
	END                       ; Program sonu