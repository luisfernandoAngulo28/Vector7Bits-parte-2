/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapadeNegocio;

/**
 *Ayudantia Fernando Angulo
 * @author hp
 */
public class VectorNBits {
    //Atributos
    int V[];//Vector de Enteros
    int dim;//Casillas que tienen los valores
    int cantidadbits;
     
    //Constructor         //5
    public VectorNBits(int cantidad,int cantbits){
        int Nbits=cantidad*cantbits;
        this.cantidadbits=cantbits;
        int NumeroEntero=Nbits/32; //2
        if (Nbits%32!=0) {
            
            NumeroEntero++;        
        }
        V=new int[NumeroEntero];
        this.dim=cantidad;
        
    }
    
    //----------------------------------------------
    //Set                 //5      81
    public void Insertar(int pos,int elemento){   //Rango de valores 0 a 2^cantidadbits -1
        if (pos<=dim) {
          int elemento1=elemento;
          int mask= (int) (Math.pow(2, cantidadbits) -1);  //12cantidadbits=1111111
          int Nbit=Calcularbits(pos); //28 desde aqui en adelante ingresa valor
           // System.out.println("Nbit="+Nbit);
          int NE=CalcularEN(pos); //0
          // System.out.println("NE="+NE);
          mask=mask<<Nbit;
         // System.out.println("mask="+mask+"=="+Integer.toBinaryString(mask));
          mask=~mask;  //00001111111111111111111111111111
         // System.out.println("mask="+mask+"=="+Integer.toBinaryString(mask));
          // System.out.println(" V[NE]="+ V[NE]+"=="+Integer.toBinaryString( V[NE]));
          //para limpiar usamos el and(&)
          V[NE]=V[NE]&mask;  
         //   System.out.println(" V[NE]="+ V[NE]+"=="+Integer.toBinaryString( V[NE]));     
          elemento=elemento<<Nbit;//00010000000000000000000000000000
          
          // System.out.println(" elemento="+ elemento+"=="+Integer.toBinaryString(elemento));
          //para meter un dato usamos el or(|)
          V[NE]=V[NE]|elemento;////00010000000000000000000000000000
            //Nbit=28
            if (Nbit+cantidadbits>32) {  //si es mayor a 32bits faltan bits 35>32
                int NumerodeBitsfaltantes=Nbit+cantidadbits-32;//3
                int mask1=(int) (Math.pow(2, cantidadbits)-1);//12cantidadbits=1111111
                mask1=mask1>>>(cantidadbits-NumerodeBitsfaltantes);
                //System.out.println("mask1="+mask1+"=="+Integer.toBinaryString(mask1));
                mask1=~mask1;
                V[NE+1]=V[NE+1]&mask1;
                //System.out.println("elemento1="+elemento1+"=="+Integer.toBinaryString(elemento1));
                elemento1=elemento1>>>(cantidadbits-NumerodeBitsfaltantes);
                //System.out.println("V[NE+1]="+V[NE+1]+"=="+Integer.toBinaryString(V[NE+1]));
                //System.out.println("elemento1="+elemento1+"=="+Integer.toBinaryString(elemento1));
                
                V[NE+1]=V[NE+1]|elemento1;
                
            }
        //    System.out.println(Integer.toBinaryString(V[0]));//00010000000000000000000000000000
            //System.out.println(Integer.toBinaryString(V[1]));//00000000000000000000000000000101
                 
        }
    }
    
                      //pos 2
    public int sacar(int pos){
        int numeroBits=Calcularbits(pos);//28
        System.out.println("numeroBits="+numeroBits);
        int numeroEntero=CalcularEN(pos);//0
        int mask=(int) (Math.pow(2, cantidadbits)-1);
       // System.out.println("mask="+mask+"=="+Integer.toBinaryString(mask));
        mask=mask<<numeroBits;
        mask=mask&V[numeroEntero];
        mask=mask>>>numeroBits;////00010000000000000000000000000000
         System.out.println("mask="+mask+"=="+Integer.toBinaryString(mask));
        //numeroBits=28;
        if ((numeroBits+cantidadbits)>32) {//si
            int mask1=(int) (Math.pow(2, cantidadbits)-1); 
            mask1=mask1>>>(32-numeroBits);//viejito recorre 4 bits
            mask1=mask1&V[numeroEntero+1];
            ////00000000000000000000000000000101
            mask1=mask1<<(32-numeroBits);
            System.out.println("mask1="+mask1+"=="+Integer.toBinaryString(mask1));
            ////00000000000000000000000001010000
            mask=mask|mask1;
            System.out.println("mask="+mask+"=="+Integer.toBinaryString(mask));
        }
       
       return mask;
        
    }
    
   
    
    public int Calcularbits(int pos) {
        
        return (((pos-1)*cantidadbits)%32);//cantidadbits
        //1*cantidadbits=
    }

    public int CalcularEN(int pos) {
        return (((pos-1)*cantidadbits)/32);//0
    }
    
    @Override
    public String toString(){
        String S="V[";
        for (int i = 1; i <=dim; i++) {
          S=S+sacar(i)+",";
        }
        S=S.substring(0, S.length()-1);
        return S+"]";
    }
    
    
  
   
    public static void main(String[] args) {
        VectorNBits A=new VectorNBits(10,7);
        A.Insertar(2, 10);
          A.Insertar(5, 81);
       //A.Insertar(1, 25);
        //System.out.println(A.sacar(5));
        System.out.println(A.toString());
    }

   
    
}
