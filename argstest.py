import argparse

parser = argparse.ArgumentParser(description="Let's test argparse!")

group1 = parser.add_argument_group('group 1')
group2 = parser.add_argument_group('group 2')

group2.add_argument('gamma', help='arg gamma')
group2.add_argument('delta', help='arg delta')
group2.add_argument('--foo', help='foo arg')

group1.add_argument('alpha', help='arg alpha')
group1.add_argument('beta', help='arg beta')
group1.add_argument('--bar', help='bar arg')


#parser.add_argument('c01', choices='abc')
#parser.add_argument('--foo', nargs=2)
#parser.add_argument('bar', nargs=1)
#parser.add_argument('b01', choices='def')

#parser.add_argument('integers', metavar='N', nargs='1', help='a list of integers')
#parser.add_argument('-m', '--mode', dest='mode', action='store', default='mode off', help='the mode')

args = parser.parse_args()

print args

#print("User specified mode: ", args.mode)
#print("User specified following integers: ", args.integers)
