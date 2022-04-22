import scala.collection.mutable.ArrayBuffer
import scala.math.random
import scala.util.Random

/**
 * A team in the RPG consisting of multiple characters
 */
final class Team(r: Random):
  private val _members: ArrayBuffer[Character] = ArrayBuffer()

  /**
   * Adds a character to the team
   * @param char the character to add
   */
  def addMember(char: Character) =
    _members += char
    () // return the unit literal
  
  /**
   * Returns true if all members of the team are dead
   */
  def isAllDead = _members.forall(!_.isAlive)
  /**
   * Returns true if all members of the team are alive
   */
  def isAllAlive = _members.forall(_.isAlive)
  /**
   * Returns a random character that is alive
   */
  def randomAliveCharacter =
    val aliveCharacters = _members.filter(_.isAlive)
    if aliveCharacters.isEmpty then
      throw Exception("All characters are dead!")
    aliveCharacters(r.between(0, aliveCharacters.size))
  /**
   * Returns a random character that is dead
   */
  def randomDeadCharacter =
    val deadCharacters = _members.filter(!_.isAlive)
    if deadCharacters.isEmpty then
      throw Exception("All characters are alive!")
    deadCharacters(r.between(0, deadCharacters.size))

  /**
   * Returns the characters of the team that are still alive
   */
  def aliveCharacters = _members.filter(_.isAlive)

  override def toString =
    val padding = paddingLength
    val names = ArrayBuffer("Members:  ")
    val hline = ArrayBuffer("----------")
    val hp = ArrayBuffer("Hitpoints:")
    val mana = ArrayBuffer("Mana:     ")
    val alive = ArrayBuffer("Status:   ")
    for char <- _members do
      names += char.toString.padTo(padding, ' ')
      hline += "-" * padding
      hp += s"${char.currentHp} / ${char.maxHp}".padTo(padding, ' ')
      mana += s"${char.currentMana} / ${char.maxMana}".padTo(padding, ' ')
      alive += (if char.isAlive then "ALIVE" else "DEAD").padTo(padding, ' ')
    val line = hline.mkString("-+-")
    s"$line\n${names.mkString(" | ")}\n" +
      s"$line\n${hp.mkString(" | ")}\n" +
      s"${mana.mkString(" | ")}\n${alive.mkString(" | ")}\n$line"

  private def paddingLength =
    // compute length of longest name
    var padding = 5
    for char <- _members do
      if char.toString.length > padding then
        padding = char.toString.length
      val hpString = char.currentHp.toString.length +
        char.maxHp.toString.length + 3
      if hpString > padding then
        padding = hpString
      val manaString = char.currentMana.toString.length +
        char.maxMana.toString.length + 3
      if manaString > padding then
        padding = manaString
    padding
      