/**
 * 
 */

class Dice {
 /**
 * @param {number} value - verdien til terningen
 */
 constructor() {
 	this.value=0;
 }
 /**
 * Get terning verdi
 * @public
 */
 getValue() {
 return this.value;
 }

/**
 * Trill ein terning
 * @public
 */
 roll() {
 	this.value = parseInt(Math.random()*6+1)
 }
}
