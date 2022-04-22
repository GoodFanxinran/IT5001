/**
 * A fighter has max hp 1200 and strength 100. Fighters
 * cost 100 gold. During each turn in the battle, the
 * fighter will select a random enemy and inflict a damage
 * equal to their strength, i.e. 100hp.
 */
class Fighter extends Character:
  val name = "Fighter"
  val maxHp = 1200
  val maxMana = 0
  protected var _currentHp = maxHp
  protected val str = 100
  protected var _currentMana = maxMana

  protected def damage = str

  // Attacks one enemy with some damage
  final def act(myTeam: Team, enemyTeam: Team) =
    val target = enemyTeam.randomAliveCharacter
    val dmg = damage
    println(s"Attack $target by ${dmg}!")
    target.gotHurt(dmg)

object Fighter:
  val COST = 100