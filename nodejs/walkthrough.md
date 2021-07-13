# Walk-through of solution

Firstly, go read the design rationale in the [README](./README.md).

With this in mind the first thing that we do is bring up a scaffold with a test, which we check fails. We fix and check-in `4b5a5b5a359d620a8338a1dcffe53c1699269a56`. <-- Where you see a hash like this, it is the git hash of the commit so you can follow along with my walk-through of this code-base by doing `git checkout <hash>`. From now on I'll use `git log --oneline` so that git prints the short hash - which has suitably small chance of collisions for our purposes!

## 4b5a5b5 Added Node.js solution scaffold

What I want to do first is to create a failing Acceptance test. I don't want to test output, because this is tricky. I can test a 'view model' however, which I can envisage as a two dimensional matrix of single chars.

Hence `diamond('B')` should produce this result:

[[ ' ', 'A', ' '],
 [ 'B', ' ', 'B'],
 [ ' ', 'A', ' ']]

We'll need to mark this test as pending, so as to keep our test suite green.

Actually writing the code, I feel like I want to test something like a 'to_str' method, so I change the API to have Diamond as an object that has a method `.to_str` which I call. I now expect the output to be ' A \nB B\n A \n'. There's a trailing newline there in order to make the test output prettier.

At this point I search for 'jasmine how to mark a test as pending' because although I think that you prepend an initial 'x' to the suite/test as per RSpec, I really can't remember.

The test suite now looks like:
```JavaScript
describe('A diamond based on B', () => {
  it('should look like', () => {
    let diamond = new Diamond('B')
    expect(diamond.to_str()).toEqual(' A \nB B\n A \n')
  })
})
```

Note no semicolons and the use of arrow functions. This is a stylistic preference. This (obviously) doesn't work, so I 'fake it till you make it' to keep the tests green and ignore my previous idea of just making the acceptance test pending. I haven't even bothered with a constructor at this point. I'm also using 'TDD as if you meant it' by writing the implementation inside the test file, with the intention to later move it to its own module when the test file becomes unwieldy.

This gives me a passing acceptance test, which I remind myself I will have to wire up to a real solution later by adding a `TODO:` comment. For now I will commit what I have.

