import random
from pathlib import Path

# tile distribution decided based on the sample inputs
# 12 tiles total, only 4 are not normal, and half is quicksand,
# other half are jump blocking tiles, also split in half
# so, 75% normal, 25% special, of which 50% quicksand and 50% jump blocking
def get_tile():
    random_value = random.randint(1, 100)
    if random_value <= 75:
        return "."
    elif random_value <= 90:
        return "#"
    elif random_value <= 95:
        return "X"
    elif random_value <= 100:
        return "J"
    else:
        return "."
    

MAX_TEST_CASES = 20
MAX_ROWS = 400
MAX_COLS = 400
# 400 is just insane to do by hand
MAX_ROWS_FOR_HUMANS = 5
MAX_COLS_FOR_HUMANS = 5
MAX_CONSECUTIVE_JUMPS = 5
MAX_JUMP_LIMIT = 10
MIN_VALUE = 1

def make_test():
    test_output_dir = Path(r'C:\Users\nikola\Documents\Disciplinas\ada\prj01')
    test_output_file = test_output_dir / 'gen_test.txt'
    with open(test_output_file, 'w') as test_file:
        test_cases = random.randint(1, MAX_TEST_CASES)
        test_file.write(f"{test_cases}\n")

        print(f"Generating {test_cases} test cases...")
        for _ in range(test_cases):
            rows = random.randint(1, MAX_ROWS)
            cols = random.randint(1, MAX_COLS)
            consecutive_jump_limit = random.randint(1, MAX_CONSECUTIVE_JUMPS)
            jump_limit = random.randint(1, MAX_JUMP_LIMIT)
            test_file.write(f"{rows} {cols} {consecutive_jump_limit} {jump_limit}\n")

            print(f"Consecutive jump limit: {consecutive_jump_limit}, jump limit: {jump_limit}.")
            print(f"Land dimensions: {rows}x{cols}.")

            print(f"Generating land...")
            for row in range(rows):
                for col in range(cols):
                    tile = None
                    # castle and start just need to be steppable, 
                    # but I'll keep them as normal
                    if(row == 0 and col == 0) or (row == rows - 1 and col == cols - 1):
                        tile = "."
                    else:
                        tile = get_tile()
                    test_file.write(f"{tile}")
                test_file.write("\n")

    print(f"Test cases generated and saved to {test_output_file}.")

if __name__ == "__main__":
    make_test()