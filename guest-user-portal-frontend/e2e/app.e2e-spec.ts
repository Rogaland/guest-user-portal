import { GuestUserPortalFrontendPage } from './app.po';

describe('guest-user-portal-frontend App', function() {
  let page: GuestUserPortalFrontendPage;

  beforeEach(() => {
    page = new GuestUserPortalFrontendPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
