# This program demonstrates the construction of basic arithmetic circuits in digital logic design.
# It includes implementations of a half adder, a full adder, and a simple Arithmetic Logic Unit (ALU).
# The half adder adds two bits and produces a sum and carry bit.
# The full adder extends this by including a carry-in bit.
# The ALU can perform addition operations based on an opcode, supporting half-addition, full-addition, and increment.

def NAND_gate(a, b):
    # NAND gate logic: returns 0 if both inputs are 1, otherwise returns 1
    if a:
        if b:
            return 0
    return 1

def NOT_gate(a):
    # NOT gate using NAND gate: NAND of a with itself
    if NAND_gate(a, a):
        return 1
    else:
        return 0  

def AND_gate(a, b):
    # AND gate using NAND gate: NOT of NAND gate result
    if NAND_gate(a, b):
        return 0
    else:
        return 1   

def OR_gate(a, b):
    # OR gate using NAND gate: NAND of NOT a and NOT b
    return NAND_gate(NAND_gate(a, a), NAND_gate(b, b))

def XOR_gate(a, b):
    # XOR gate using NAND, OR, and AND gates
    return AND_gate(NAND_gate(a, b), OR_gate(a, b))


def half_adder(a, b):
    # Calculate the sum bit using XOR gate
    s = XOR_gate(a, b)
    # Calculate the carry bit using AND gate
    c = AND_gate(a, b)
    # Return the sum and carry bits as a tuple
    return (s, c)

# Test the half adder with all possible inputs
print(half_adder(0, 0))  # Expected output: (0, 0)
print(half_adder(0, 1))  # Expected output: (1, 0)
print(half_adder(1, 0))  # Expected output: (1, 0)
print(half_adder(1, 1))  # Expected output: (0, 1)

def full_adder(a, b, c):
    # Use a half adder to add a and b
    s1, c1 = half_adder(a, b)
    # Use another half adder to add the sum from the first half adder and the carry-in bit
    s, c2 = half_adder(s1, c)
    # Combine the carry bits from both half adders using an OR gate to get the final carry-out
    c_out = OR_gate(c1, c2)
    # Return the final sum and carry-out bits as a tuple
    return (s, c_out)

print("testing full_adder")
# Test the full adder with various inputs
print(full_adder(0, 0, 0))  # Expected output: (0, 0)
print(full_adder(1, 1, 1))  # Expected output: (1, 1)
print(full_adder(0, 1, 1))  # Expected output: (0, 1)
print(full_adder(1, 1, 0))  # Expected output: (0, 1)

def ALU(a, b, c, opcode):
    # Determine the operation based on the opcode
    if opcode == 0:
        # Half adder operation
        s, c_out = half_adder(a, b)
    elif opcode == 1:
        # Full adder operation
        s, c_out = full_adder(a, b, c)
    elif opcode == 2:
        # Increment operation (add 1 to a)
        s, c_out = full_adder(a, 1, 0)
    # Return the result of the operation
    return (s, c_out)

print("testing ALU:")
# Test the ALU with different inputs and opcodes
print(ALU(0, 0, 1, 0))  # Expected output: (0, 0) (half-adder)
print(ALU(0, 0, 1, 1))  # Expected output: (1, 0) (full-adder)
print(ALU(1, 1, 1, 0))  # Expected output: (0, 1) (half-adder)
print(ALU(1, 1, 1, 1))  # Expected output: (1, 1) (full-adder)
print(ALU(1, 1, 1, 2))  # Expected output: (0, 1) (increment by 1)
