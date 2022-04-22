parent = {'Amy': 'Ben', 'May': 'Tom', 'Tom': 'Ben',
          'Ben': 'Howard', 'Howard': 'George', 'Frank': 'Amy',
            'Joe': 'Bill', 'Bill': 'Mary', 'Mary': 'Philip', 'Simon': 'Bill',
          'Zoe': 'Mary'}


def is_ancestor(name1, name2, parent): # check if name1 is an ancestor of name2
    ancestor = []
    for i in range(4):
        if name2 in parent:
            _ancestor = parent[name2]
            ancestor.append(_ancestor)
            name2 = _ancestor
    if name1 in ancestor:
        is_ancestor = True
    else:
        is_ancestor = False
    return is_ancestor

def is_related(name1,name2,parent):
    ancestor1 = []
    length = len(parent)
    for i in range(length):
        if name1 in parent:
            _ancestor = parent[name1]
            ancestor1.append(_ancestor)
            name1 = _ancestor
    # print(ancestor1)
    ancestor2 = []
    for i in range(length):
        if name2 in parent:
            _ancestor = parent[name2]
            ancestor2.append(_ancestor)
            name2 = _ancestor
    # print(ancestor2)
    if (name1 in ancestor2) or (name2 in ancestor1):
        return True
    else:
        return False


# print("Is Mary an ancestor of Simon?")
# print(is_ancestor('Mary','Simon',parent))
# print("Is Zoe an ancestor of Joe?")
# print(is_ancestor('Zoe','Joe',parent))
# print()


# print("Is Joe is_related to Philip?")
# # is_related('Joe','Philip',parent)
# print(is_related('Joe','Philip',parent))

print("Is Amy is_related to May?")
is_related('Amy','May',parent)
print(is_related('Amy','May',parent))

# print("Is Amy is_related to Philip?")
# print(is_related('Amy','Philip',parent))
# print()


# parent['Ben']='Philip' #modify the dictionary so that Philip is Ben's parent
# print("After Philip became Ben\'s parent...")
# print("Is Amy is_related to Philip?")
# print(is_related('Amy','Philip',parent))

