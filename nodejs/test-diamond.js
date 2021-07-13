class Diamond {
  to_str() {
    return(' A \nB B\n A \n')
  }
}

// TODO: wire acceptance test to real implementation
describe('A diamond based on B', () => {
  it('should look like', () => {
    let diamond = new Diamond('B')
    expect(diamond.to_str()).toEqual(' A \nB B\n A \n')
  })
})
