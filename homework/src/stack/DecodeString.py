class DecodeString():
    def decodeString(self,s):
        stack = []
        # "", prefix, 1 times (prefix,count)pair
        stack.append(["",1])
        num = ""
        for char in s:
            if char.isdigit():
                num+=char
            elif char=='[':
                stack.append(["",int(num)])
                num = ""
            elif char==']':
                str,count = stack.pop()
                stack[-1][0] += str*count
            else:
                stack[-1][0] +=char
        return stack[0][0]
