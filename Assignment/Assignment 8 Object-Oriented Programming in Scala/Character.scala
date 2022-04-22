/**
 * A character in the RPG
 */
abstract class Character:
  import Character.id
  // public members
  val name: String
  val maxHp: Int
  val maxMana: Int

  // protected members
  protected var _currentHp: Int
  protected var _currentMana: Int
  protected var _isAlive: Boolean = true

  // private members
  private val _id = id
  id += 1

  /**
   * Gets this character to act on a random character
   * in either the friendly or enemy team.
   * @param myTeam the friendly team
   * @param enemyTeam the enemy team
   */
  def act(myTeam: Team, enemyTeam: Team): Unit

  /**
   * Causes damage to this character
   * @param damage the damage dealt to this character
   */
  def gotHurt(damage: Int): Unit = 
    if damage >= this._currentHp then
      // dead
      _currentHp = 0
      _isAlive = false
      println(f"$this died!")
    else
      // still alive
      _currentHp -= damage
      println(f"$this hurt with remaining hp ${_currentHp}")

  /**
   * Revives this character with some penalty of
   * the maximum hp. For example, a 0.3 penalty means
   * the character will be revived but with only
   * (1 - 0.3) = 0.7 * max hp
   * @param penalty the penalty of the maximum hp this
   *                character is revived with
   */
  def revive(penalty: Double) =
    // should not call this method if character is already alive
    if isAlive then
      throw Exception("Character is already alive!")
    _isAlive = true
    _currentHp = (maxHp * (1.0 - penalty)).toInt
    println(s"$this revived with hp ${_currentHp}")
  
  /**
   * Returns True if the character is alive
   */
  def isAlive = _isAlive

  /**
   * Returns the current hp of the character
   */
  def currentHp = _currentHp

  /**
   * Returns the current mana of the character
   */
  def currentMana = _currentMana
  override def toString = s"${this.name} ${this._id}"

object Character:
  private var id = 1