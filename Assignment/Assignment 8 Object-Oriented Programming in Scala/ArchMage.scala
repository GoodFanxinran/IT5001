open class ArchMage extends Mage:
  val name = "ArchMage"
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
    _currentMana -= SPELL_MANA_COST
    val team_num = 0
    val target = enemyTeam.randomAliveCharacter
    for i <- myTeam:
        if i.isAlive then
            team_num += 1
    if team_num == 2 then
        println(f"Cast KABOOOOOOM ! (Damage 800) to every enemy!")
        println(f"Fighter died!")
        _int = 800
    else then
        println(f"Strike $target with spell!")
    target.gotHurt(_int)

object ArchMage:
  val COST = 600