/**
 * 
 */
class DiceController {

    constructor(root) {
        this.root = root;
        this.run = this.run.bind(this);
        root.getElementsByTagName("button")[0].addEventListener("click", this.run, true);

    }

    run() {
        const myDice = new Dice();
        myDice.throwDice();
        let value = myDice.value;
        this.root.querySelector('span').innerText = value;

    }
}

function init() {
    const rootElement = document.getElementById("root");
    console.log(rootElement);
    new DiceController(rootElement);


}
document.addEventListener("DOMContentLoaded", init);

/*const controller = new DiceController("root");
document.addEventListener("DOMContentLoaded",controller.run,true);*/








