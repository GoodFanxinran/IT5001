import scala.util.Random
import scala.annotation.tailrec
import scala.io.StdIn

val RNG = Random(1) // for setting a constant seed, change if necessary
val STARTING_GOLD = 1000
val CHARACTER_SELECTION: Array[(String, Int, () => Character)] = Array(
  ("Fighter", Fighter.COST, () => Fighter()),
  ("Mage", Mage.COST, () => Mage()),
  /* Uncomment the following to test each character
  ("Berserker", Berserker.COST, () => Berserker()),
  ("ArchMage", ArchMage.COST, () => ArchMage()),
  ("Necromancer", Necromancer.COST, () => Necromancer())
  */
)

@main def Main =
  val enemyTeam = createRandomTeam()
  val userTeam = userChooseTeam()
  val winner = runBattle(userTeam, enemyTeam, turn = "USER")
  printGameConfiguration(userTeam, enemyTeam)
  println(s"$winner wins!")

@tailrec
def runBattle(user: Team, enemy: Team, turn: String): String =
  if user.isAllDead then
    return "ENEMY"
  if enemy.isAllDead then
    return "USER"

  printGameConfiguration(user, enemy)
  println(s"$turn turn")
  if turn == "USER" then
    // Let user select a character that is alive
    val availableCharacters = user.aliveCharacters
    val len = availableCharacters.length
    (0 until len).map(x => (x, availableCharacters(x)))
      .foreach(x => println(s"${x._1}: ${x._2}"))
    val selection = safeReadInt("Choose a character to act... ", 0, len)
    // Get that character to act on the enemy team
    availableCharacters(selection).act(user, enemy)
    StdIn.readLine("Press Enter to continue...\n")
    runBattle(user, enemy, "ENEMY")
  else
    // Randomly select one living character from the enemy team
    // to act
    enemy.randomAliveCharacter.act(enemy, user)
    StdIn.readLine("Press Enter to continue...\n")
    runBattle(user, enemy, "USER")

@tailrec
def createRandomTeam(gold: Int = STARTING_GOLD, t: Team = Team(RNG)): Team =
  val availableCharacters = getAvailableCharacters(gold)
  if availableCharacters.length == 0 then t
  else
    val (_, cost, constr) = availableCharacters(RNG.between(0, availableCharacters.length))
    t.addMember(constr())
    createRandomTeam(gold - cost, t)

@tailrec
def userChooseTeam(gold: Int = STARTING_GOLD, t: Team = Team(RNG)): Team =
  println(s"You have $gold gold left.")
  val availableCharacters = getAvailableCharacters(gold)
  if availableCharacters.length == 0 then t
  else
    (0 until availableCharacters.length)
      .map(x => (x, availableCharacters(x)._1, availableCharacters(x)._2))
      .foreach((idx, name, cost) => println(f"$idx: $name ($cost gold)"))
    val selection = safeReadInt("Please enter your chosen character... ", 0, availableCharacters.length)
    val (_, cost, constr) = availableCharacters(selection)
    t.addMember(constr())
    userChooseTeam(gold - cost, t)

@tailrec
def safeReadInt(msg: String, low: Int, high: Int): Int =
  val ip = StdIn.readLine(msg)
  try
    val res = ip.toInt
    if (low until high).contains(res) then res
    else throw Exception()
  catch
    case e: Exception =>
      println(s"Please enter an integer in [$low, $high).")
      safeReadInt(msg, low, high)
  
def printGameConfiguration(user: Team, enemy: Team) =
  println("=== Enemy Team ===")
  println(enemy)
  println("=== Your Team ===")
  println(user)

def getAvailableCharacters(currentGold: Int) =
  CHARACTER_SELECTION.filter(_._2 <= currentGold)
