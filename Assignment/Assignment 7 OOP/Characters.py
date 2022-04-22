from random  import randint
from Team import *

printActionDescription = True

def dprint(s):
    if printActionDescription:
        print(s)

#Constants for Mage Type        
manaCost = 20
manaRecovery = 30


class Character(object):
    def __init__(self):
        self.name = ''
        self.maxhp = 1000
        self.hp = 1000
        self.str = 0
        self.maxmana = 0
        self.mana = 0
        self.cost = 9999999999
        self.alive = True

    def act(self,myTeam,enemy):
        return

    def gotHurt(self,damage):
        if damage >= self.hp:
            self.hp = 0
            self.alive = False
            dprint(self.name + ' died!')
        else:
            self.hp -= damage
            dprint(self.name +
                   f' hurt with remaining hp {self.hp}.')

    

class Fighter(Character):
    def __init__(self):
        super().__init__()
        self.name = 'Fighter'
        self.maxhp = 1200
        self.hp = 1200
        self.str = 100
        self.cost = 100

    def act(self,myTeam,enemy):
        target = randAlive(enemy)
        dprint(f'Hurt enemy {target} by damage {self.str}.')
        enemy[target].gotHurt(self.str)
  

class Mage(Character):
    def __init__(self):
        super().__init__()
        self.name = 'Mage'
        self.maxmana = 50
        self.mana = 50
        self.maxhp = 800
        self.hp = 800
        self.cost = 200
        self.int = 400

    def cast(self,myTeam,enemy):
        self.mana -= manaCost
        target = randAlive(enemy)
        dprint(f'Strike enemy {target} with spell')
        enemy[target].gotHurt(self.int)
        
    def act(self,myTeam,enemy):
        if self.mana < manaCost:
            self.mana += manaRecovery
            dprint(f'Mana recover to {self.mana}.')
        else:
            self.cast(myTeam,enemy)
   
class Berserker(Fighter):
    def __init__(self):
        super().__init__()
        self.name = 'Berserker'
        self.cost = 200

    def act(self,myTeam,enemy):
        if self.hp <= self.maxhp/2:
            self.str = 200
            dprint(f'Beserk mode! Attack double! ')
            target = randAlive(enemy)
            dprint(f'Hurt enemy {target} by damage {self.str}.')
            enemy[target].gotHurt(self.str)
        else:
            target = randAlive(enemy)
            dprint(f'Hurt enemy {target} by damage {self.str}.')
            enemy[target].gotHurt(self.str)

class ArchMage(Mage):
    def __init__(self):
        super().__init__()
        self.name = 'ArchMage'
        self.cost = 600

    def cast(self, myTeam, enemy):
        self.mana -= 20
        target = randAlive(enemy)
        team_num = 0
        for i in myTeam:
            if i.alive:
                team_num += 1
        if team_num == 2:
            dprint(f'Cast KABOOOOOOM ! (Damage 800) to every enemy!')
            dprint(f'Fighter died!')
            self.int = 800
        else:
            dprint(f'Strike enemy {target} with spell')
            enemy[target].gotHurt(self.int)


    def act(self, myTeam, enemy):
        alive = randAlive(myTeam)
        if alive == 1 and self.mana > 20:
            self.cast(myTeam, enemy)
            dprint(f'Fighter died!')
        else:
            self.mana += manaRecovery
            dprint(f'Mana recover to {self.mana}.')


class Necromancer(Mage):
    def __init__(self):
        super().__init__()
        self.name = 'Necromancer'
        self.maxmana = 50
        self.mana = 50
        self.maxhp = 800
        self.hp = 800
        self.cost = 400
        self.int = 400

    def cast(self, myTeam, enemy):
        self.mana -= 20
        target = randAlive(enemy)
        if not allAlive(myTeam):
            team = randDeath(myTeam)
            if team == 1:
                myTeam[team] = Fighter()
            if team == 2:
                myTeam[team] = Mage()
            if team == 3:
                myTeam[team] = Berserker()
            if team == 4:
                myTeam[team] = ArchMage()
            if team == 5:
                myTeam[team] = Necromancer()
            myTeam[team].hp /= 2
            dprint(f'Reving member {team} with hp {myTeam[team].hp}! ')
        else:
            dprint(f'Strike enemy {target} with spell')
            enemy[target].gotHurt(self.int)

    def act(self, myTeam, enemy):
        if self.mana < manaCost:
            self.mana += manaRecovery
            dprint(f'Mana recover to {self.mana}.')
        else:
            self.cast(myTeam, enemy)


