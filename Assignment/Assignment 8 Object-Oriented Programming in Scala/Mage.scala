/**
 * A mage has max hp 800, max mana 50, intelligence 400 and
 * costs 200. Casting a spell costs 20 mana.
 * At each turn, if the mage has enough mana, they
 * will cast a spell on a random living character in the enemy
 * team, inflicting damage equal to the mage's intelligence.
 * Otherwise, the mage will meditate to gain 30 mana.
 */
open class Mage extends Character:
  val name = "Mage"
  val maxHp = 800
  val maxMana = 50
  protected var _currentHp = maxHp
  val str: Int = 0
  protected var _currentMana = maxMana
  protected val _int = 400
  private val SPELL_MANA_COST = 20
  private val MANA_RECOVERY = 30

  // Casts a spell if it has mana, meditate otherwise
  final def act(myTeam: Team, enemyTeam: Team): Unit =
    if _currentMana < SPELL_MANA_COST then
      // must meditate
      _currentMana += MANA_RECOVERY
      println(f"Mana recover to ${_currentMana}")
    else
      // cast spell
      _currentMana -= SPELL_MANA_COST
      cast(myTeam, enemyTeam)

  protected def cast(myTeam: Team, enemyTeam: Team): Unit =
    val target = enemyTeam.randomAliveCharacter
    println(f"Strike $target with spell!")
    target.gotHurt(_int)

object Mage:
  val COST = 200