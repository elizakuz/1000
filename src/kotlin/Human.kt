/* Trick-taking game for three players "1000"
by Sokolova Polina & Kuzmina Liza */

package kotlin

import java.util.*

public class Human() : Player() {
    internal fun humanInput() : Boolean {
        val s = readLine() ?: ""
        println()
        if (s == "Д") { return true }
        return false
    }

    override internal fun activeClick() : Card {
        val card = click(handCards)
        Game.activeSuit = card.suit
        return card
    }

    override internal fun passiveClick() : Card = click(availableCards())

    private fun click(cards : ArrayList<Card>) : Card {
        print("Доступные для хода карты: ")
        Game.printCards(cards)
        println("\n")
        println("Ваш ход: ")
        val args = getArgs(cards.size - 1)
        val card = cards[args]
        handCards.remove(card)
        Computer().inaccessibleCards.add(card)
        return card
    }

    override internal fun askPointsDivision() : Boolean {
        println("Расписать? Д/Н")
        return humanInput()
    }

    override protected fun askPlayerToRaise(nextBid : Int) : Int {
        println("$nextBid? Д/Н")
        if (!humanInput()) { return 0 }
        return nextBid
    }

    override internal fun finalObligation() {
        println("Выберите ставку")
        val s = readLine()?.toInt() ?: 0
        println()
        val maxSum = sumWithMarriage()
        if (s >= obligation) { obligation = s }
        if (s > maxSum) { obligation = maxSum }
        println("Ставка: $obligation\n")
    }

    override protected fun chooseCardsToGive() {
        print("Ваши карты: ")
        Game.printCards(handCards)
        println("\n")
        println("Выберите две карты для сноса")
        firstCardNumber  = getArgs(handCards.size - 1)
        secondCardNumber = getArgs(handCards.size - 1)
    }

    internal fun getArgs(range : Int) : Int {
        var args : Int = readLine()?.toInt() ?: getArgs(range)
        if (args > range || args < 0) {
            args = getArgs(range)
        }
        return args
    }
}