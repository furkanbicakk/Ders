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
KONUM		EQU	0x23	;Konum Bilgisini Tutma: 0 Dikey, 1 Yatay

;******************************************************************************
		
ORG     0x000             	; Baslama Vektoru
nop
goto    BASLA              	; BASLA Etiketine Git

;******************************************************************************
;Gecikme Alt Programi		

GECIKME				
	MOVLW	0x08
	MOVWF	GECIKME1	
	
	MOVLW	0x25
	MOVWF	GECIKME2
	MOVLW	0x01
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

;******************************************************************************
;Kaydirma Alt Programlari
	
SAGAKAYMA
	BCF PORTC,5	;5. bit clear
	BSF PORTC,0	;0. bit set
	RETURN
	
ASAGIKAYMA
	BCF PORTB,7	;7. bit clear
	BSF PORTB,0	;0. bit set
	RETURN
	
YUKARIKAYMA
	BCF PORTB,0	;0. bit clear
	BSF PORTB,7	;7. bit set
	RETURN
	
SOLAKAYMA
	BCF PORTC,0	;0. bit clear
	BSF PORTC,5	;5. bit set
	RETURN

;******************************************************************************
;Dondurme Alt Programlari
	
DIKEYEDONDUR
	BTFSC	PORTB,0
	MOVLW	b'01000011'
	BTFSC	PORTB,1
	MOVLW	b'00000111'
	BTFSC	PORTB,2
	MOVLW	b'00001110'
	BTFSC	PORTB,3
	MOVLW	b'00011100'
	BTFSC	PORTB,4
	MOVLW	b'00111000'
	BTFSC	PORTB,5
	MOVLW	b'01110000'
	BTFSC	PORTB,6
	MOVLW	b'01100001'
	MOVWF	PORTB
	
	BTFSS   PORTC,0 ; C port kontrolleri
	GOTO	$+2
	BTFSS   PORTC,1
	GOTO	$+2
	BTFSS   PORTC,2
	GOTO	$+2
	MOVLW	b'00000010'
	BTFSS   PORTC,1 
	GOTO	$+2
	BTFSS   PORTC,2
	GOTO	$+2
	BTFSS   PORTC,3
	GOTO	$+2
	MOVLW	b'00000100'
	BTFSS   PORTC,2 
	GOTO	$+2
	BTFSS   PORTC,3
	GOTO	$+2
	BTFSS   PORTC,4
	GOTO	$+2
	MOVLW	b'00001000'
	BTFSS   PORTC,3 ; 
	GOTO	$+2
	BTFSS   PORTC,4
	GOTO	$+2
	BTFSS   PORTC,0
	GOTO	$+2
	MOVLW	b'00010000'
	BTFSS   PORTC,4 ; C port kontrolleri
	GOTO	$+2
	BTFSS   PORTC,0
	GOTO	$+2
	BTFSS   PORTC,1
	GOTO	$+2
	MOVLW	b'00000001'
	MOVWF	PORTC
	
	;konumu dikey (0) olarak guncelleme
	MOVLW	0X00
	MOVWF	KONUM
	
	RETURN
	
YATAYADONDUR
	BTFSC	PORTC,0
	MOVLW	b'00010011'
	BTFSC	PORTC,1
	MOVLW	b'00000111'
	BTFSC	PORTC,2
	MOVLW	b'00001110'
	BTFSC	PORTC,3
	MOVLW	b'00011100'
	BTFSC	PORTC,4
	MOVLW	b'00011001'
	MOVWF	PORTC
	
	BTFSS   PORTB,0 ; B port kontrolleri
	GOTO	$+2
	BTFSS   PORTB,1
	GOTO	$+2
	BTFSS   PORTB,2
	GOTO	$+2
	MOVLW	b'00000010'
	BTFSS   PORTB,1 
	GOTO	$+2
	BTFSS   PORTB,2
	GOTO	$+2
	BTFSS   PORTB,3
	GOTO	$+2
	MOVLW	b'00000100'
	BTFSS   PORTB,2 
	GOTO	$+2
	BTFSS   PORTB,3
	GOTO	$+2
	BTFSS   PORTB,4
	GOTO	$+2
	MOVLW	b'00001000'
	BTFSS   PORTB,3 ; 
	GOTO	$+2
	BTFSS   PORTB,4
	GOTO	$+2
	BTFSS   PORTB,5
	GOTO	$+2
	MOVLW	b'00010000'
	BTFSS   PORTB,4 ; 
	GOTO	$+2
	BTFSS   PORTB,5
	GOTO	$+2
	BTFSS   PORTB,6
	GOTO	$+2
	MOVLW	b'00100000'
	BTFSS   PORTB,5 ; 
	GOTO	$+2
	BTFSS   PORTB,6
	GOTO	$+2
	BTFSS   PORTB,0
	GOTO	$+2
	MOVLW	b'01000000'
	BTFSS   PORTB,6 ; 
	GOTO	$+2
	BTFSS   PORTB,0
	GOTO	$+2
	BTFSS   PORTB,1
	GOTO	$+2
	MOVLW	b'00000001'
	MOVWF	PORTB
	
	;konumu yatay (1) olarak guncelleme
	MOVLW	0X01
	MOVWF	KONUM		    
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
	;ilk durum
	MOVLW	b'00011100'	    
	MOVWF	PORTB
	MOVLW	b'00000100'	    
	MOVWF	PORTC
	
	;konum ilk durum dikey (0) olarak ayarlama
	MOVLW	0X00
	MOVWF	KONUM
	
	;baslangicta butonlari dinleme
	BTFSS	PORTA,0		
	GOTO	A0.SAG
	BTFSS	PORTA,1		
	GOTO	A1.ASAGI
	BTFSS	PORTA,2		
	GOTO	A2.YUKARI
	BTFSS	PORTA,3		
	GOTO	A3.SOL
	
	GOTO	BASLANGIC
	
;******************************************************************************
;Butona basilma durumlari

A0.SAG; A0 SAG BUTONA BASILMASI DURUMU *****************************
	
	CALL GECIKME
	
	BTFSS	KONUM,0 ; konum yatay (1) ise yataya dondurmeyi atla
	CALL YATAYADONDUR

	BCF STATUS,0 ;sorun olmamasi icin status c biti 0'la
	RLF PORTC ;port c'yi sola kaydirma (ekranda saga)

	BTFSC	PORTC,5 ;5. biti 0 ise saga kaymayi atla
	CALL SAGAKAYMA
	
	;buton dinleme
	BTFSS	PORTA,0		
	GOTO	A0.SAG
	BTFSS	PORTA,1		
	GOTO	A1.ASAGI
	BTFSS	PORTA,2		
	GOTO	A2.YUKARI
	BTFSS	PORTA,3		
	GOTO	A3.SOL
	;;;;;;;;;;;;;;;
	
	GOTO A0.SAG

A1.ASAGI; A1 ASAGI BUTONA BASILMASI DURUMU *****************************
	
	CALL GECIKME
	
	BTFSC	KONUM,0 ;konum dikey (0) ise dikeye donmeyi atla
	CALL DIKEYEDONDUR
	
	BCF STATUS,0 ;sorun olmamasi icin status c biti 0'la
	RLF PORTB ;port b'yi sola kaydir (ekranda asagi)
	
	BTFSC	PORTB,7 ;7. biti 0 ise asagi kaymayi atla
	CALL ASAGIKAYMA	
	
	;buton dinleme
	BTFSS	PORTA,0		
	GOTO	A0.SAG
	BTFSS	PORTA,1		
	GOTO	A1.ASAGI
	BTFSS	PORTA,2		
	GOTO	A2.YUKARI
	BTFSS	PORTA,3		
	GOTO	A3.SOL
	;;;;;;;;;;;;;;;
	
	GOTO A1.ASAGI

A2.YUKARI; A2 YUKARI BUTONA BASILMASI DURUMU *****************************
	
	CALL GECIKME
	
	BTFSC	KONUM,0 ;konum dikey (0) ise dikeye donmeyi atla
	CALL DIKEYEDONDUR
	
	BTFSC PORTB,0 ;0. biti 0 ise yukari kaymayi atla
	CALL YUKARIKAYMA
	
	BCF STATUS,0 ;sorun olmamasi icin status c biti 0'la
	RRF PORTB ;port b'yi saga kaydir (ekranda yukari)
	
	;buton dinleme
	BTFSS	PORTA,0		
	GOTO	A0.SAG
	BTFSS	PORTA,1		
	GOTO	A1.ASAGI
	BTFSS	PORTA,2		
	GOTO	A2.YUKARI
	BTFSS	PORTA,3		
	GOTO	A3.SOL
	;;;;;;;;;;;;;;;
	
	GOTO A2.YUKARI
	
A3.SOL; A3 SOL BUTONA BASILMASI DURUMU *****************************
	
	CALL GECIKME
	
	BTFSS	KONUM,0 ;konum yatay (1) ise yataya dondurmeyi atla
	CALL YATAYADONDUR
	
	BTFSC	PORTC,0 ;0. biti 0 ise sola kaymayi atla
	CALL SOLAKAYMA
	
	BCF STATUS,0 ;sorun olmamasi icin status c biti 0'la
	RRF PORTC ;port c'yi saga kaydirma (ekranda sola)
	
	;buton dinleme
	BTFSS	PORTA,0		
	GOTO	A0.SAG
	BTFSS	PORTA,1		
	GOTO	A1.ASAGI
	BTFSS	PORTA,2		
	GOTO	A2.YUKARI
	BTFSS	PORTA,3		
	GOTO	A3.SOL
	;;;;;;;;;;;;;;;
	
	GOTO A3.SOL

END                       ; Program sonu