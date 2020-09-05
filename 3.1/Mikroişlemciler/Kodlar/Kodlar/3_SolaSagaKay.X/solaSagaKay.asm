; Bu ornekte gecikme, timer ile degil dongu ile yap?lacakt?r
;  4 MHz kristal icin her bir komut 1mikrosn
; ?ç içe dongu ile yaklas?k 1sn gecikme olur. 
; NOP bir cycle bekleme yapaca?? unutulmamal?
	
; Her bir sn de portB de once sola en sona gelince saga kay?yor
	
	;KODLAMA SABLONU

	list		p=16f877A	; hangi pic
	#include	<p16f877A.inc>	; SFR register 'lar?n tan?mland??? kutuphane
	
	;__CONFIG _CP_OFF & _WDT_OFF & _BODEN_OFF & _PWRTE_ON & _XT_OSC & _WRT_OFF & _LVP_ON & _CPD_OFF
	__CONFIG H'3F31'
; WDT, ossilatör gibi register ayarlar?

	
;KULLANILACAK DEGISKENLER

GECIKME1	EQU	0x20    ;GEC?KME 1. DONGU
GECIKME2	EQU	0x21    ;GECIKME 2. DONGU
GECIKME3	EQU	0x22    ;GECIKME 3.DONGU
SOLSAG		EQU	0x23	;EGER ICINDEKI DEGER 0x00 ISE SOLA 0x01 ISE SAGA


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

	
;*********************************************TMR KULLANMADAN GECIKME***************************************************


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
	
	
;*********************************************************************************	
SSDON
	BTFSC	SOLSAG,0	    ;EGER SAGSOL=0x00 ?SE SOLA 1 ?SE SAGA DON
	GOTO	SAGAGIT
	GOTO	SOLAGIT
SAGAGIT	RRF	PORTB
	RETURN
SOLAGIT	RLF	PORTB
	RETURN
;*************************************************************************************

SOLKONTROL
	BTFSS	PORTB,7		    ; EN SOLA ULASTI MI?
	RETURN			    ; ULASMADI ISE SOLA KAYMAYA DEVAM ET
	MOVLW	0x01		    ; ULASTI ISE SAGA DONMESI ICIN SOLSAG DEGISKENINE 1 YUKLE
	MOVWF	SOLSAG	    
;***************************************************************************************
SAGKONTROL
	BTFSS	SOLSAG,0	    ; ILK PROGRAM BASLARKEN ?LK BIT 1 D?R. HATA OLMASIN D?YE SOLSAG 'INDA 0X01 OLMASI KONTROL ED?LMEL?
	RETURN			    ; ILK CALISIYORSA BU CAGRIMI DIKKATE ALMA
	BTFSS	PORTB,0		    ; EGER ONCEDEN SOLA DONMUS ISE VE SIMDI SAGA DONUYORSA VE EN SAGDAKI BIT 1 OLMUS ISE ATLA
	RETURN			    ; SARTLAR SAGLAMIYORSA SAGA KAYMAYA DEVAM ET
	MOVLW	0x00		    ; ULASTI IE SOLA DONMESI ICIN SOLSAG DEGISKENINE 0 YUKLE
	MOVWF	SOLSAG	

;*****************************************************************************************
BASLA

	CLRF	PORTB		    ; portb de bir?ey olms?n
	BANKSEL	TRISB		    ; portb nin hepsini ç?k?? yap
	CLRF	TRISB
	
	BCF	STATUS,RP0	    ; Portb ye deger yuklemek icin bank1 e gec
	MOVLW	0X00
	MOVWF	SOLSAG		    ; BASTA SOLA DONSUN

	MOVLW	0X01		    ; PORT B 'YI BASTA 00000001 YAP
YUKLE	MOVWF	PORTB
		    
	
DD
	CALL	GECIKME		    ;1sn GEC?K
	CALL	SSDON
	CALL	SOLKONTROL
	CALL	SAGKONTROL
	GOTO	DD


	END			    ; Program sonu

;*****************************************************************************************************************************




