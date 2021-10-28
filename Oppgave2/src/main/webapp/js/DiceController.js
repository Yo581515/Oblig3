/**
 * 
 */
class DiceController {
 /**
 * @param {String} run - 
 * @param {Dice} rollDice - 
 * @param {String} root - 
 */
 constructor(root) {
	this.run = this.run.bind(this);
	this.rollDice = this.rollDice.bind(this);
	this.root = root;
 }

 run() {
	//btRef=document.getElementById(this.root).querySelector("*[data-dicebutton]");
	let btRef=document.getElementById(this.root).getElementsByTagName("button")[0];
	btRef.addEventListener("click",this.rollDice,true);	
}

 rollDice() {
	const terning = new Dice("root");
	terning.roll();
	this.diceoutput = document.getElementById(this.root).getElementsByTagName("span")[0];
	this.diceoutput.innerText = terning.getValue();
 }
}

const controller = new DiceController("root");
document.addEventListener("DOMContentLoaded",controller.run,true);








