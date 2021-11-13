/**
 * 
 */

class Dice {
    //piblic felt
    maxnumber;
    value = null;

    constructor(maxnumber = 6) {
        this.maxnumber = maxnumber;
    }

    throwDice() {
        this.value = 1 + Math.trunc(this.maxnumber * Math.random());
    }
}

const d = new Dice(6);
d.throwDice();

console.log(d.value);