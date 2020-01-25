import { TestBed } from '@angular/core/testing';

import { CharacterHeader } from './character.service';

describe('CharacterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CharacterHeader = TestBed.get(CharacterHeader);
    expect(service).toBeTruthy();
  });
});
