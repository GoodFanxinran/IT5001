class Necromancer extends Character:

    val name = "Necromancer"
    val maxHp = 800
    val maxMana = 50
    val str: Int = 0
    protected var _currentHp = maxHp
    protected var _currentMana = maxMana
    protected val _int = 400
    private val SPELL_MANA_COST = 20
    private val MANA_RECOVERY = 30

    protected def damage = str

    // Attacks one enemy with some damage
    final def act(myTeam: Team, enemyTeam: Team): Unit =
        if _currentMana < SPELL_MANA_COST then
            // must meditate
            _currentMana += MANA_RECOVERY
            println(f"Mana recover to ${_currentMana}")
        else if anyDead(myTeam) then
            // revive a random dead team member
            _currentMana -= SPELL_MANA_COST
            castrRaiseDead(myTeam, enemyTeam)
        else
            // cast spell
            _currentMana -= SPELL_MANA_COST
            cast(myTeam, enemyTeam)


    protected def cast(myTeam: Team, enemyTeam: Team): Unit =
        val target = enemyTeam.randomAliveCharacter
        println(f"Strike $target with spell!")
        target.gotHurt(_int)

    protected def castrRaiseDead(myTeam: Team, enemyTeam: Team): Unit =
        val target = myTeam.randomDeadCharacter
        target.revive(0.5)

    protected def anyDead(myTeam: Team): Boolean = 
        !myTeam.isAllAlive
    
object Necromancer:
    val COST = 400