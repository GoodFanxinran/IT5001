class Berserker extends Fighter:
  val name = "Berserker"
  val maxHp = 1200
  val maxMana = 0
  protected var _currentHp = maxHp
  protected val str = 100
  protected var _currentMana = maxMana

  protected def damage = str

  // Attacks one enemy with some damage
  final def act(myTeam: Team, enemyTeam: Team) =
      if _currentHp <= maxHp then
          str = 200
          println(s"Beserk mode! Attack double!")
    val target = enemyTeam.randomAliveCharacter
    val dmg = damage
    println(s"Attack $target by ${dmg}!")
    target.gotHurt(dmg)
    
    

object Berserker:
  val COST = 200
