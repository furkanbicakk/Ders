; Bu ornekte RA da 0-5 aras? hangi butona bas?lm?s ise RB de o led yanacak
; RA 'lar?n varsay?lan olarak Analog oldu?unu unutmay?n
; PORT A 6 bitlik buffer-supported bir porttur
; Comparator ozelligi bulunmaktad?r
; RA4 ST (h?zl?, 1 volt alt? 0, üstü 1, tabi 5V VDD icin) iken di?erleri TTL (karars?z bolge var) dir
	
; NOT!! BU ORNEKTE PULL-UP AKT?F YAN? RA LARA 5 V GEL?YOR. BUTONA BASINCA 0 GEL?R. (PULL-DOWN TAM TERS?)
; RA da hangi tusa basilmis ise RB de o led yanacak
	

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
	
	
	
;*********************************TMR SIZ 1SN DELAY ******************************

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

PORTA_AYARLA
	BANKSEL	TRISA
	MOVLW	0x06
	MOVWF	ADCON1		; ADC PAS?F
	MOVLW	b'00111111'	; RA0-5 G?R??
	MOVWF	TRISA
	BCF	STATUS,RP0	; TEKRAR BANK0 A GEC
	RETURN
	
PORTB_AYARLA
	BANKSEL	TRISB
	CLRF	TRISB
	BANKSEL	PORTB
	CLRF	PORTB
	RETURN
BASLA
	CALL	PORTA_AYARLA
	CALL	PORTB_AYARLA
	
KONTROL	
	BTFSS	PORTA,0		;UNUTMA! PULL-UP CALISILIYOR
	GOTO	YAK_0
	BTFSS	PORTA,1		
	GOTO	YAK_1
	BTFSS	PORTA,2		
	GOTO	YAK_2
	BTFSS	PORTA,3		
	GOTO	YAK_3
	BTFSS	PORTA,4		
	GOTO	YAK_4
	BTFSS	PORTA,5		
	GOTO	YAK_5
	GOTO	KONTROL
	
YAK_0
	MOVLW	b'00000001'
	MOVWF	PORTB
	GOTO	TEKRAR
YAK_1
	MOVLW	b'00000010'
	MOVWF	PORTB
	GOTO	TEKRAR
YAK_2
	MOVLW	b'00000100'
	MOVWF	PORTB
	GOTO	TEKRAR
YAK_3
	MOVLW	b'00001000'
	MOVWF	PORTB
	GOTO	TEKRAR

YAK_4
	MOVLW	b'00010000'
	MOVWF	PORTB
	GOTO	TEKRAR
	
YAK_5
	MOVLW	b'00100000'
	MOVWF	PORTB
	GOTO	TEKRAR
	

TEKRAR
	GOTO KONTROL
	
	END                       ; Program sonu


