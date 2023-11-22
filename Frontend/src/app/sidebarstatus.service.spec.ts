import { TestBed } from '@angular/core/testing';

import { SidebarstatusService } from './sidebarstatus.service';

describe('SidebarstatusService', () => {
  let service: SidebarstatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SidebarstatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
