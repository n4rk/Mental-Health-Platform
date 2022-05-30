import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoteDetailsComponent } from './note-details.component';

describe('NoteDetailsComponent', () => {
  let component: NoteDetailsComponent;
  let fixture: ComponentFixture<NoteDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NoteDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NoteDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
