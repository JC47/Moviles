// All usefull objects and shortcuts to simplify coding
let objects = ['bicycle', 'bicycle', 'leaf', 'leaf', 'cube', 'cube', 'anchor', 'anchor', 'paper-plane-o', 'paper-plane-o', 'bolt', 'bolt', 'bomb', 'bomb', 'diamond', 'diamond'];

    // Useful selectors shortened
    var container = document.getElementsByClassName('container');
    var scorePanel = document.getElementsByClassName('score-panel');
    var movesTag = document.getElementById('moves');
    var timer = document.getElementById('timer');
    var restart = document.getElementsByClassName('restart');
    var deck = document.getElementById('deck');

    // Set variables to shorten code
    var nowTime;
    var allOpen = [];
    var match = 0;
    var second = 0;
    var moves = 0;
    var wait = 420;
    var totalCard = objects.length / 2;

// Shuffling function: enables that no two games have the same card arrangement
function shuffle(array) {
    let currentIndex = array.length, temporaryValue, randomIndex;

    while (currentIndex !== 0) {
        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;
        temporaryValue = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temporaryValue;
    }
    return array;
}

// The function init() enables the game to begin
function init() {

    // The shuffle function shuffles the objects array
    let allCards = shuffle(objects);
    deck.innerHTML = '';
    // The game starts with no matching cards and zero moves
    match = 0;
    moves = 0;
    movesTag.innerHTML = 0;

    for (let i = 0; i < allCards.length; i++) {
        deck.innerHTML+='<li class="card"><i class="fa fa-' + allCards[i] + '"></i></li>';
    }
    addCardListener();

    resetTimer(nowTime);
    second = 0;
    timer.innerHTML = ''+second;
    initTime();
}

// Add boostrap modal alert window showing time, moves, score it took to finish the game, toggles when all pairs are matched.
function gameOver(moves) {
  alert("Terminaste en " + second + " segundos, con " +moves+ " movimientos");
  init();
}

// This function allows each card to be validated that is an equal match to another card that is clicked on to stay open.
// If cards do not match, both cards are flipped back over.
let addCardListener = function () {

    var cardsTemp = deck.getElementsByClassName("card");
    for(let i = 0; i < cardsTemp.length; i++){
      cardsTemp[i].addEventListener('click',function() {
        if(cardsTemp[i].className == 'show' || cardsTemp[i].className == 'match'){return true;}

        let card = cardsTemp[i].innerHTML.toString();
        allOpen.push(card);
        cardsTemp[i].className += ' open show';

        if (allOpen.length > 1) {
            if (card === allOpen[0]) {
                var a = deck.getElementsByClassName('open');
                setTimeout(function() {
                  for(let i=0; i<a.length;i++){
                    a[i].className = "card match";
                  }
                },wait/4);
                setTimeout(function() {
                  for(let i=0; i<a.length;i++){
                    a[i].className = "card match";
                  }
                },wait/4);
                match++;
            } else {
              var x = deck.getElementsByClassName('open');
              setTimeout(function() {
                for(let i=0; i<x.length;i++){
                  x[i].className = "card notmatch";
                }
              },wait);
              setTimeout(function() {
                for(let i=0; i<x.length;i++){
                  x[i].className = "card notmatch";
                }
              },wait);
            }
            allOpen = [];
            moves++;
            movesTag.innerHTML = moves;
        }

        if (totalCard === match) {
            setTimeout(function () {
                gameOver(moves);
            }, 500);
        }

      },false);
    }
}

// Initiates the timer as soon as the game is loaded
function initTime() {
    nowTime = setInterval(function () {
        timer.innerHTML = ''+second;
        second = second + 1
    }, 1000);
}

// Resets the timer when the game ends or is restarted
function resetTimer(timer) {
    if (timer) {
        clearInterval(timer);
    }
}

init();
