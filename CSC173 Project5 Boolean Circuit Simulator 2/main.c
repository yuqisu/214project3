/*
 * File: main.c
 * Creator: George Ferguson
 * Created: Mon Nov 28 14:11:17 2016
 * Time-stamp: <Mon Nov 28 14:22:27 EST 2016 ferguson>
 */
#include <stdio.h>
#include <stdlib.h>
#include "Circuit.h"

/**
 * Two AND gates connected to make a 3-input AND circuit.
 */
static Circuit* Circuits_and3() {
	Value* in0 = new_Value(false);
	Value* in1 = new_Value(false);
	Value* in2 = new_Value(false);
	Gate* and0 = new_AndGate(in0, in1);
	Gate* and1 = new_AndGate(Gate_getOutput(and0), in2);

	Value** inputs = new_Value_array(3);
	inputs[0] = in0;
	inputs[1] = in1;
	inputs[2] = in2;
	Value** outputs = new_Value_array(1);
	outputs[0] = Gate_getOutput(and1);
	Gate** gates = new_Gate_array(2);
	gates[0] = and0;
	gates[1] = and1;
	return new_Circuit(3, inputs, 1, outputs, 2, gates);
}


static Circuit* Circuit_notand2(){
	Value* in0 = new_Value(false);
	Value* in1 = new_Value(false);
	Value* in2 = new_Value(false);
	Gate *not = new_Inverter(in1);
	Gate *and0 = new_AndGate(in0,Gate_getOutput(not));
	Gate *and1 = new_AndGate(in1,in2);
	Gate *or1 = new_OrGate(Gate_getOutput(and0),Gate_getOutput(and1));

	Value **inputs = new_Value_array(3);
	inputs[0] = in0;
	inputs[1] = in1;
	inputs[2] = in2;
	Value** outputs = new_Value_array(1);
	outputs[0] = Gate_getOutput(or1);
	Gate** gates = new_Gate_array(4);
	gates[0] = not;
	gates[1] = and0;
	gates[2] = and1;
	gates[3] = or1;
	return new_Circuit(3, inputs, 1, outputs, 4, gates);

}

static Circuit* Circuit_notnand2nor(){
	Value* in0 = new_Value(false);
	Value* in1 = new_Value(false);
	Value* in2 = new_Value(false);
	Gate *not = new_Inverter(in1);
	Gate *and0 = new_AndGate(in0,Gate_getOutput(not));
	Gate *and1 = new_AndGate(in1,in2);
	Gate *nand0 = new_Inverter(Gate_getOutput(and0));
	Gate *nand1 = new_Inverter(Gate_getOutput(and1));
	Gate *or1 = new_OrGate(Gate_getOutput(nand0),Gate_getOutput(nand1));
	Gate *nor1 = new_Inverter(Gate_getOutput(or1));

	Value **inputs = new_Value_array(3);
	inputs[0] = in0;
	inputs[1] = in1;
	inputs[2] = in2;
	Value** outputs = new_Value_array(1);
	outputs[0] = Gate_getOutput(nor1);
	Gate** gates = new_Gate_array(7);
	gates[0] = not;
	gates[1] = and0;
	gates[2] = and1;
	gates[3] = nand0;
	gates[4] = nand1;
	gates[5] = or1;
	gates[6] = nor1;
	return new_Circuit(3, inputs, 1, outputs, 7, gates);

}

static Circuit* OneBitter(){
	Value* in0 = new_Value(false);
	Value* in1 = new_Value(false);
	Value* in2 = new_Value(false);

	Gate *not0 = new_Inverter(in0);
	Gate *not1 = new_Inverter(in1);
	Gate *not2 = new_Inverter(in2);
	Gate *gate1 = new_And3Gate(Gate_getOutput(not0),Gate_getOutput(not1),in2);
	Gate *gate2 = new_And3Gate(Gate_getOutput(not0),in1,Gate_getOutput(not2));
	Gate *gate3 = new_And3Gate(Gate_getOutput(not0),in1,in2);
	Gate *gate4 = new_And3Gate(in0,Gate_getOutput(not1),Gate_getOutput(not2));
	Gate *gate5 = new_And3Gate(in0,Gate_getOutput(not1),in2);
	Gate *gate6 = new_And3Gate(in0,in1,Gate_getOutput(not2));
	Gate *gate7 = new_And3Gate(in0,in1,in2);
	Gate *z = new_Or4Gate(Gate_getOutput(gate1),Gate_getOutput(gate2),Gate_getOutput(gate4),Gate_getOutput(gate7));
	Gate *d = new_Or4Gate(Gate_getOutput(gate3),Gate_getOutput(gate5),Gate_getOutput(gate6),Gate_getOutput(gate7));

	Value **inputs = new_Value_array(3);
	inputs[0] = in0;
	inputs[1] = in1;
	inputs[2] = in2;

	Value** outputs = new_Value_array(2);
	outputs[0] = Gate_getOutput(z);
	outputs[1] = Gate_getOutput(d);
	Gate** gates = new_Gate_array(12);
	gates[0] = not0;
	gates[1] = not1;
	gates[2] = not2;
	gates[3] = gate1;
	gates[4] = gate2;
	gates[5] = gate3;
	gates[6] = gate4;
	gates[7] = gate5;
	gates[8] = gate6;
	gates[9] = gate7;
	gates[10] = z;
	gates[11] = d;
	
	return new_Circuit(3, inputs, 2, outputs, 12, gates);

	

}

static char* b2s(bool b) {
	return b ? "T" : "F";
}

static void test3In2Out(Circuit* circuit,bool in0, bool in1, bool in2){
	Circuit_setInput(circuit, 0, in0);
	Circuit_setInput(circuit, 1, in1);
	Circuit_setInput(circuit, 2, in2);
	Circuit_update(circuit);
	bool out0 = Circuit_getOutput(circuit, 0);
	bool out1 = Circuit_getOutput(circuit, 1);

	printf("%s %s %s -> %s %s\n", b2s(in0), b2s(in1), b2s(in2), b2s(out0),b2s(out1));

}


static void test3In1Out(Circuit* circuit, bool in0, bool in1, bool in2) {

	Circuit_setInput(circuit, 0, in0);
	Circuit_setInput(circuit, 1, in1);
	Circuit_setInput(circuit, 2, in2);
	// Circuit_dump(circuit);
	Circuit_update(circuit);
	bool out0 = Circuit_getOutput(circuit, 0);
	printf("%s %s %s -> %s\n", b2s(in0), b2s(in1), b2s(in2), b2s(out0));
}
static void testCircuitAB(Circuit* circuit){
 bool a,b,c;
 int i,j,k;
 for(i=0;i<2;i++){
  if(i%2==0){
   a=true;
  }else{
   a=false;
   // printf("%s\n", "false ~~~");
  }
  for(j=0;j<2;j++){
   if(j%2==0){
    b=true;
   }else{
    b=false;
   }
   for(k=0;k<2;k++){
    if(k%2==0){
     c=true;
    }else{
     c=false;
    }
    test3In1Out(circuit, a, b,c);
   }
  }
 }
}
static void testCircuitD(Circuit* circuit){
 bool a,b,c;
 int i,j,k;
 for(i=0;i<2;i++){
  if(i%2==0){
   a=true;
  }else{
   a=false;
   // printf("%s\n", "false ~~~");
  }
  for(j=0;j<2;j++){
   if(j%2==0){
    b=true;
   }else{
    b=false;
   }
   for(k=0;k<2;k++){
    if(k%2==0){
     c=true;
    }else{
     c=false;
    }
    test3In2Out(circuit, a, b,c);
   }
  }
 }
}


int main(int argc, char **argv) {
	
	Circuit* c = OneBitter();
	
	testCircuitAB(c);

	// printf("Some input(s) false: should be false\n");
	// test3In1Out(c, false, false, true);
	// printf("All inputs true: should be true\n");
	// test3In1Out(c, false, false, false);
}
